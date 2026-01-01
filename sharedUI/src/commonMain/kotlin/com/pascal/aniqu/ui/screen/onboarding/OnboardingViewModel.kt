package com.pascal.aniqu.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import com.pascal.aniqu.ui.screen.onboarding.state.OnboardingUIState
import com.pascal.aniqu.ui.screen.profile.state.ProfileUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel(): ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUIState())
    val uiState: StateFlow<OnboardingUIState> = _uiState.asStateFlow()

    fun resetError() {
        _uiState.update { it.copy(error = false to "") }
    }
}