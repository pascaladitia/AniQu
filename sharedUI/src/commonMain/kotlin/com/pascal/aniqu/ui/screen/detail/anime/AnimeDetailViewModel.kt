package com.pascal.aniqu.ui.screen.detail.anime

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
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

            val episodeFlow = detailFlow
                .mapNotNull { detail ->
                    detail?.episodesList?.firstOrNull()?.episodeId
                }
                .flatMapLatest { episodeId ->
                    animeUseCase.getAnimeEpisode(episodeId)
                }

            val streamingFlow = episodeFlow
                .mapNotNull { episode ->
                    episode?.streamingQualities?.firstOrNull()?.servers?.firstOrNull()?.serverId
                }
                .flatMapLatest { serverId ->
                    animeUseCase.getAnimeStreaming(serverId)
                }

            combine(
                detailFlow,
                episodeFlow,
                streamingFlow
            ) { detail, episode, streaming ->
                Triple(detail, episode, streaming)
            }
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = true to (e.message ?: "Unknown error")
                        )
                    }
                }
                .collect { (detail, episode, streaming) ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            animeId = slug,
                            animeDetail = detail,
                            episodeDetail = episode,
                            streamingUrl = streaming?.url.orEmpty()
                        )
                    }
                }
        }
    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}