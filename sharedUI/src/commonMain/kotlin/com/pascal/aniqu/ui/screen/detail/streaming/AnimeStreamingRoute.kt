package com.pascal.aniqu.ui.screen.detail.streaming

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close
import com.pascal.aniqu.domain.model.anime.Stream
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.screen.detail.streaming.state.LocalAnimeStreamingEvent
import com.pascal.aniqu.utils.ScreenOrientation
import com.pascal.aniqu.utils.setScreenOrientation
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun AnimeStreamingRoute(
    stream: Stream? = null,
    viewModel: AnimeStreamingViewModel = koinInject<AnimeStreamingViewModel>(),
    onNavBack: () -> Unit
) {
    val event = LocalAnimeStreamingEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        if (stream?.isEmbed == true) setScreenOrientation(ScreenOrientation.LANDSCAPE)

        onDispose {
            setScreenOrientation(ScreenOrientation.PORTRAIT)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadAnimeStreaming(stream)
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
        LocalAnimeStreamingEvent provides event.copy(
            onNavBack = onNavBack,
            onEpisodeSelected = {},
            onDownloadSelected = {}
        )
    ) {
        AnimeStreamingScreen(
            uiState = uiState
        )
    }
}