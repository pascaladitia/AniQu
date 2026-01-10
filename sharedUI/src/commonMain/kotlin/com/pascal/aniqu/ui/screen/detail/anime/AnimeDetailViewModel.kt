package com.pascal.aniqu.ui.screen.detail.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val animeUseCase: AnimeUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AnimeDetailUIState())
    val uiState: StateFlow<AnimeDetailUIState> = _uiState.asStateFlow()

    fun loadAnimeDetail(slug: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true,) }

            animeUseCase.getAnimeDetail(slug)
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = true to e.message.toString(),
                        )
                    }
                }
                .collect { result ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            animeDetail = result
                        )
                    }
                }
        }
    }

    fun resetError() {
        _uiState.update { it.copy() }
    }
}