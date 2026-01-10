package com.pascal.aniqu.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.ui.screen.search.state.SearchUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val animeUseCase: AnimeUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState: StateFlow<SearchUIState> = _uiState.asStateFlow()

    fun loadAnimeGenre() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true,) }

            animeUseCase.getAnimeGenre()
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            error = true to e.message.toString(),
                        )
                    }
                }
                .collect { result ->
                    _uiState.update {
                        it.copy(
                            genreList = result.toMutableList()
                        )
                    }

                    loadAnimeGenre(result.first().title)
                }
        }
    }

    fun loadAnimeGenre(slug: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true,) }

            animeUseCase.getAnimeGenre(slug)
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            error = true to e.message.toString(),
                        )
                    }
                }
                .collect { result ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            animeByGenreList = result.toMutableList()
                        )
                    }
                }
        }
    }

    fun loadAnimeSearch(key: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true,) }

            animeUseCase.getAnimeSearch(key)
                .catch { e ->
                    _uiState.update {
                        it.copy(
                            error = true to e.message.toString(),
                        )
                    }
                }
                .collect { result ->
                    _uiState.update {
                        it.copy(
                            animeList = result.toMutableList(),
                        )
                    }
                }
        }
    }

    fun resetError() {
        _uiState.update { it.copy() }
    }
}