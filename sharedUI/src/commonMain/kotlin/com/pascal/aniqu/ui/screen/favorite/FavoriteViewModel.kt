package com.pascal.aniqu.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import com.pascal.aniqu.ui.screen.favorite.state.FavoriteUIState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUIState())
    val uiState: StateFlow<FavoriteUIState> = _uiState.asStateFlow()

    fun loadFavorite() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true,) }

            localUseCase.getFavorite()
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
                            animeList = result.toImmutableList(),
                        )
                    }
                }
        }
    }

    fun loadSearch(key: String) {
        val filter = _uiState.value.animeList.filter { it.title.contains(key) }
        _uiState.update {
            it.copy(
                animeList = filter.toImmutableList()
            )
        }
    }

    fun resetError() {
        _uiState.update { it.copy() }
    }
}