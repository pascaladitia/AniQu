package com.pascal.aniqu.ui.screen.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.PullRefreshComponent
import com.pascal.aniqu.ui.screen.search.state.LocalSearchEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinInject<SearchViewModel>(),
    onDetail: () -> Unit
) {
    val event = LocalSearchEvent.current
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
        LocalSearchEvent provides event.copy(
            onSearch = {},
            onGenre = {}
        )
    ) {
        PullRefreshComponent(
            onRefresh = {

            }
        ) {
            SearchScreen(
                modifier = modifier,
                uiState = uiState
            )
        }
    }
}