package com.pascal.aniqu.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.PullRefreshComponent
import com.pascal.aniqu.ui.screen.favorite.state.LocalFavoriteEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun FavoriteRoute(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinInject<FavoriteViewModel>(),
    onDetail: (String) -> Unit
) {
    val event = LocalFavoriteEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadFavorite()
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
        LocalFavoriteEvent provides event.copy(
            onSearch = viewModel::loadSearch,
            onDetail = onDetail
        )
    ) {
        PullRefreshComponent(
            onRefresh = viewModel::loadFavorite
        ) {
            FavoriteScreen(
                modifier = modifier,
                uiState = uiState
            )
        }
    }
}