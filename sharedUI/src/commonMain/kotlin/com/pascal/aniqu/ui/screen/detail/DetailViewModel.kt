@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pascal.aniqu.ui.screen.detail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.lifecycle.ViewModel
import com.pascal.aniqu.domain.usecase.local.LocalUseCase

class DetailViewModel(
    private val localUseCase: LocalUseCase
) : ViewModel() {
}