@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pascal.aniqu.ui.screen.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.LoadingScreen
import com.pascal.aniqu.ui.component.screenUtils.PullRefreshComponent
import com.pascal.aniqu.ui.component.screenUtils.TopAppBarComponent
import com.pascal.aniqu.ui.screen.home.component.HomeAccount
import com.pascal.aniqu.ui.screen.home.component.HomeCard
import com.pascal.aniqu.ui.screen.home.component.HomeMenu
import com.pascal.aniqu.ui.screen.home.component.homeMarket
import com.pascal.aniqu.ui.screen.home.state.HomeUIState
import com.pascal.aniqu.ui.screen.home.state.LocalHomeEvent
import com.pascal.aniqu.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close

@Composable
fun HomeScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: HomeViewModel = koinInject<HomeViewModel>(),
    onDetail: (FavoritesEntity?) -> Unit
) {
    val event = LocalHomeEvent.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setTransition(sharedTransitionScope, animatedVisibilityScope)
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

    CompositionLocalProvider(
        LocalHomeEvent provides event.copy(
            onDetail = onDetail
        )
    ) {
        PullRefreshComponent(
            onRefresh = {
                viewModel.loadInit()
            }
        ) {
            HomeContent(uiState = uiState)
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeUIState = HomeUIState()
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            TopAppBarComponent(
                title = "Hi, Selamat Datang",
                rightIcon1 = Icons.Outlined.Search,
                rightIcon2 = Icons.Filled.Notifications,
                rightIcon3 = Icons.Filled.Settings
            )
        }

        item {
            HomeAccount()
        }

        item {
            HomeMenu()
        }

        item {
            HomeCard()
        }

        homeMarket(uiState = uiState)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    AppTheme {
        HomeContent()
    }
}