package com.pascal.aniqu.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.LoadingScreen
import com.pascal.aniqu.ui.screen.profile.component.ProfileThemeBottom
import com.pascal.aniqu.ui.screen.profile.state.LocalProfileEvent
import com.pascal.aniqu.ui.screen.profile.state.ProfileUIState
import com.pascal.aniqu.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close

@Composable
fun PortofolioScreen(
    viewModel: ProfileViewModel = koinInject<ProfileViewModel>(),
    onBookMark: () -> Unit
) {
    val event = LocalProfileEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showThemeSheet by rememberSaveable { mutableStateOf(false) }

    if (showThemeSheet) {
        ProfileThemeBottom(
            initialSelection = uiState.themeMode,
            onApply = { selection ->
                showThemeSheet = false
                viewModel.onThemeChanged(selection)
            },
            onDismiss = {
                showThemeSheet = false
            }
        )
    }

    if (uiState.isLoading) LoadingScreen()

    if (uiState.error.first) {
        ShowDialog(
            message = uiState.error.second,
            textButton = stringResource(Res.string.close)
        ) {
            viewModel.resetError()
        }
    }

    CompositionLocalProvider(
        LocalProfileEvent provides event.copy(
            onBookmark = onBookMark,
            onTheme = { showThemeSheet = true }
        )
    ) {
        ProfileContent(uiState = uiState)
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    uiState: ProfileUIState = ProfileUIState()
) {

}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    AppTheme {
        ProfileContent()
    }
}