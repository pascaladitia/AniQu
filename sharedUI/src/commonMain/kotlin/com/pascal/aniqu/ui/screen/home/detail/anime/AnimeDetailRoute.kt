package com.pascal.aniqu.ui.screen.home.detail.anime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.PullRefreshComponent
import com.pascal.aniqu.ui.screen.home.detail.anime.state.LocalAnimeDetailEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun AnimeDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: AnimeDetailViewModel = koinInject<AnimeDetailViewModel>(),
    onDetail: () -> Unit
) {
    val event = LocalAnimeDetailEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.error.first) {
        ShowDialog(
            message = uiState.error.second,
            textButton = stringResource(Res.string.close)
        ) {
            viewModel.resetError()
        }
    }

    CompositionLocalProvider(
        LocalAnimeDetailEvent provides event.copy(
            onDetail
        )
    ) {
        PullRefreshComponent(
            onRefresh = {

            }
        ) {
            AnimeDetailScreen(
                modifier = modifier,
                uiState = uiState
            )
        }
    }
}