package com.pascal.aniqu.ui.screen.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.aniqu.ui.screen.onboarding.state.LocalOnboardingEvent
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier
) {
    val event = LocalOnboardingEvent.current

    Column(
        modifier = modifier.fillMaxSize()
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    AppTheme {
        OnboardingScreen()
    }
}