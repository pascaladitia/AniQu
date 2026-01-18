package com.pascal.aniqu.ui.screen.manga

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.PullRefreshComponent
import com.pascal.aniqu.ui.screen.manga.state.LocalMangaEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun MangaRoute(
    viewModel: MangaViewModel = koinInject<MangaViewModel>(),
    onDetail: (String) -> Unit
) {
    val event = LocalMangaEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadMangaHome()
    }

    if (uiState.error.first) {
        ShowDialog(
            message = uiState.error.second,
            textButton = stringResource(Res.string.close)
        ) {
            viewModel.resetError()
        }
    }

    CompositionLocalProvider(
        LocalMangaEvent provides event.copy(
            onDetail = onDetail
        )
    ) {
        PullRefreshComponent(
            onRefresh = viewModel::loadMangaHome
        ) {
            MangaScreen(
                uiState = uiState
            )
        }
    }
}