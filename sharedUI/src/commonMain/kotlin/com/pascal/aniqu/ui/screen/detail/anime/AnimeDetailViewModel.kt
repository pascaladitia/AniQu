package com.pascal.aniqu.ui.screen.detail.anime

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.model.anime.Download
import com.pascal.aniqu.domain.model.anime.Stream
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val animeUseCase: AnimeUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AnimeDetailUIState())
    val uiState: StateFlow<AnimeDetailUIState> = _uiState.asStateFlow()

    fun setTransition(
        sharedTransitionScope: SharedTransitionScope,
        animatedVisibilityScope: AnimatedVisibilityScope
    ) {
        _uiState.update {
            it.copy(
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadAnimeDetail(slug: String?) {
        if (slug.isNullOrBlank()) return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = false to ""
                )
            }

            val detailFlow = animeUseCase.getAnimeDetail(slug)

            val streamingFlow = detailFlow
                .mapNotNull { detail ->
                    detail?.episodes?.firstOrNull()?.slug
                }
                .flatMapLatest { serverId ->
                    animeUseCase.getAnimeStreaming(serverId)
                }

            val genresFlow = detailFlow
                .mapNotNull { detail ->
                    detail?.genres?.firstOrNull()
                }
                .flatMapLatest { slug ->
                    animeUseCase.getAnimeGenre(slug)
                }

            combine(
                detailFlow,
                streamingFlow,
                genresFlow
            ) { detail, streaming, genres ->
                Triple(detail, streaming, genres)
            }
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = true to (e.message ?: "Unknown error")
                        )
                    }
                }
                .collect { (detail, streaming, genres) ->
                    val filterStreaming = streaming?.downloads?.filterMp4UniqueByResolution()
                    val filterEmbed = streaming?.streams?.filterStreamingByServer()

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            animeId = slug,
                            animeDetail = detail,
                            recomendList = genres.toImmutableList(),
                            downloadList = filterStreaming?.toImmutableList() ?: persistentListOf(),
                            streamList = filterEmbed?.toImmutableList() ?: persistentListOf(),
                            downloadUrl = filterStreaming?.firstOrNull()?.url.orEmpty(),
                            streamUrl = filterEmbed?.firstOrNull()?.url.orEmpty()
                        )
                    }
                }
        }
    }

    fun List<Download>.filterMp4UniqueByResolution(): List<Download> {
        return this
            .filter { it.url.endsWith(".mp4", ignoreCase = true) }
            .distinctBy { it.resolution }
    }

    fun List<Stream>.filterStreamingByServer(): List<Stream> {
        return this.distinctBy { it.server }
    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}