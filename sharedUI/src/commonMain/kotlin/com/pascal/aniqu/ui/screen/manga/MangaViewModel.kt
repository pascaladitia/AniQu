package com.pascal.aniqu.ui.screen.manga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.usecase.manga.MangaUseCase
import com.pascal.aniqu.ui.screen.manga.state.MangaUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MangaViewModel(
    private val mangaUseCase: MangaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MangaUIState())
    val uiState: StateFlow<MangaUIState> = _uiState.asStateFlow()

    fun loadMangaHome() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true,) }

            mangaUseCase.getMangaHome()
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
                            mangaResponse = result
                        )
                    }
                }
        }
    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}