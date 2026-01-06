package com.pascal.aniqu.ui.screen.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import com.pascal.aniqu.domain.usecase.remote.RemoteUseCase
import com.pascal.aniqu.ui.screen.watchlist.state.WatchListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WatchListViewModel(
    private val remoteUseCase: RemoteUseCase,
    private val localUseCase: LocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WatchListUIState())
    val uiState: StateFlow<WatchListUIState> = _uiState.asStateFlow()

    fun loadInit() {

    }

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}

enum class WatchListTab(val title: String) {
    MY_LIST("My List"),
    aniqu_PICKS("aniqu+ Picks"),
    IDEAS("Ideas")
}