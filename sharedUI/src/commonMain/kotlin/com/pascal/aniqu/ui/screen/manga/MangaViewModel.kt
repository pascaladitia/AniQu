package com.pascal.aniqu.ui.screen.manga

import androidx.lifecycle.ViewModel
import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.ui.screen.manga.state.MangaUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MangaViewModel(
    private val animeUseCase: AnimeUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MangaUIState())
    val uiState: StateFlow<MangaUIState> = _uiState.asStateFlow()

    fun loadInit() {

    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}