package com.pascal.aniqu.ui.screen.detail.manga

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.LoadingScreen
import com.pascal.aniqu.ui.screen.detail.manga.state.LocalMangaDetailEvent
import com.pascal.aniqu.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun MangaDetailRoute(
    viewModel: MangaDetailViewModel = koinInject<MangaDetailViewModel>(),
    onBookMark: () -> Unit
) {
    val event = LocalMangaDetailEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


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
        LocalMangaDetailEvent provides event.copy(
            onBookmark = onBookMark
        )
    ) {
        MangaDetailScreen(uiState = uiState)
    }
}

@Preview(showBackground = true)
@Composable
private fun MangaDetailPreview() {
    AppTheme {
        MangaDetailScreen()
    }
}