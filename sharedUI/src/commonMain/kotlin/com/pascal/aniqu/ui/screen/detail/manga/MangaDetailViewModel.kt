package com.pascal.aniqu.ui.screen.detail.manga

import androidx.lifecycle.ViewModel
import com.pascal.aniqu.ui.screen.detail.manga.state.MangaDetailUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MangaDetailViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(MangaDetailUIState())
    val uiState: StateFlow<MangaDetailUIState> = _uiState.asStateFlow()

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}
