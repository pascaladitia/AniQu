package com.pascal.aniqu.ui.screen.detail.anime

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.PullRefreshComponent
import com.pascal.aniqu.ui.screen.detail.anime.state.LocalAnimeDetailEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun AnimeDetailRoute(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    slug: String? = "",
    viewModel: AnimeDetailViewModel = koinInject<AnimeDetailViewModel>(),
    onNavBack: () -> Unit
) {
    val event = LocalAnimeDetailEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setTransition(sharedTransitionScope, animatedVisibilityScope)
        viewModel.loadAnimeDetail(slug)
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
        LocalAnimeDetailEvent provides event.copy(
            onNavBack = onNavBack,
            onEpisodeSelected = {},
            onDownloadSelected = {}
        )
    ) {
        PullRefreshComponent(
            onRefresh = {
                viewModel.loadAnimeDetail(slug)
            }
        ) {
            AnimeDetailScreen(
                uiState = uiState
            )
        }
    }
}