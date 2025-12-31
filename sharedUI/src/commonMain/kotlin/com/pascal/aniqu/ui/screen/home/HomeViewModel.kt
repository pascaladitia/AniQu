@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pascal.aniqu.ui.screen.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.pascal.aniqu.domain.mapper.getFavoriteTitlesFlow
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import com.pascal.aniqu.domain.usecase.news.RemoteUseCase
import com.pascal.aniqu.ui.screen.home.state.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val remoteUseCase: RemoteUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    private val _animeResponse = MutableStateFlow(PagingData.empty<Anime>())
    val animeResponse: StateFlow<PagingData<Anime>> = _animeResponse

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

    fun loadInit() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = false) }

            combine(
                remoteUseCase.getMarketHighlight(),
                localUseCase.getFavoriteTitlesFlow()
            ) { highlight, favorites ->
                _uiState.value.copy(
                    isLoading = false,
                    marketHighlight = highlight
                )
            }.catch { e ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = true to (e.message ?: "Unknown error")
                    )
                }
            }.collect { newState -> _uiState.update { newState } }
        }
    }

    fun loadAnime() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            remoteUseCase.getAnimeList()
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = true to e.message.toString()
                        )
                    }
                }
                .collect { result ->
                    _uiState.update { it.copy(isLoading = false) }
                    _animeResponse.value = result
                }
        }
    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}