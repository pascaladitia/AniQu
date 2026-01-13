package com.pascal.aniqu.ui.screen.detail.anime

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.mapper.mapToStreamList
import com.pascal.aniqu.domain.model.anime.Stream
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
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
        if (slug.isNullOrBlank() || uiState.value.animeDetail != null) return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    isLoadingStream = true,
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
                            isLoadingStream = false,
                            error = true to (e.message ?: "Unknown error")
                        )
                    }
                }
                .collect { (detail, streaming, genres) ->
                    val streamingList = mapToStreamList(
                        downloads = streaming?.downloads,
                        streams = streaming?.streams
                    )

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoadingStream = false,
                            animeId = slug,
                            animeDetail = detail,
                            recomendList = genres.toImmutableList(),
                            streamList = streamingList.toImmutableList(),
                            streamSelected = streamingList.firstOrNull()
                        )
                    }
                }
        }
    }

    fun loadAnimeStream(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingStream = true) }

            animeUseCase.getAnimeStreaming(id)
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoadingStream = false,
                            error = true to e.message.toString()
                        )
                    }
                }
                .collect { result ->
                    val streamingList = mapToStreamList(
                        downloads = result?.downloads,
                        streams = result?.streams
                    )

                    _uiState.update {
                        it.copy(
                            isLoadingStream = false,
                            streamList = streamingList.toImmutableList(),
                            streamSelected = streamingList.firstOrNull()
                        )
                    }
                }
        }
    }

    fun streamSelected(stream: Stream) {
        _uiState.update {
            it.copy(streamSelected = stream)
        }
    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}