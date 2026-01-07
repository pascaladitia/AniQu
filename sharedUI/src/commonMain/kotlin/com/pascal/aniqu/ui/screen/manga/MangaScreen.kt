package com.pascal.aniqu.ui.screen.manga

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.LoadingScreen
import com.pascal.aniqu.ui.screen.manga.state.LocalMangaEvent
import com.pascal.aniqu.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun MangaScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: WatchListViewModel = koinInject<WatchListViewModel>(),
    onDetail: () -> Unit
) {
    val event = LocalMangaEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadInit()
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
}

@Preview(showBackground = true)
@Composable
private fun WatchListPreview() {
    AppTheme {
    }
}