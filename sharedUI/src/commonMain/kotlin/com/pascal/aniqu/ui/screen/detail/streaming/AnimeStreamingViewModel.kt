package com.pascal.aniqu.ui.screen.detail.streaming

import androidx.lifecycle.ViewModel
import com.pascal.aniqu.domain.model.anime.Stream
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.ui.screen.detail.streaming.state.AnimeStreamingUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AnimeStreamingViewModel(
    private val animeUseCase: AnimeUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AnimeStreamingUIState())
    val uiState: StateFlow<AnimeStreamingUIState> = _uiState.asStateFlow()

    fun loadAnimeStreaming(stream: Stream?) {

        if (stream != null) {
            _uiState.update {
                it.copy(
                    stream = stream
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    error = true to "Video not found"
                )
            }
        }
    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}