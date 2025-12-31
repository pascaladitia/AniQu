package com.pascal.aniqu.ui.screen.watchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pascal.aniqu.ui.component.dialog.ShowDialog
import com.pascal.aniqu.ui.component.screenUtils.LoadingScreen
import com.pascal.aniqu.ui.component.screenUtils.PullRefreshComponent
import com.pascal.aniqu.ui.component.screenUtils.SelectableTextBorderComponent
import com.pascal.aniqu.ui.screen.watchlist.component.WatchListStock
import com.pascal.aniqu.ui.screen.watchlist.state.LocalWatchListEvent
import com.pascal.aniqu.ui.screen.watchlist.state.WatchListUIState
import com.pascal.aniqu.ui.theme.AppTheme
import com.pascal.aniqu.ui.theme.Blue500
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.close

@Composable
fun WatchListScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: WatchListViewModel = koinInject<WatchListViewModel>(),
    onDetail: () -> Unit
) {
    val event = LocalWatchListEvent.current
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

    CompositionLocalProvider(
        LocalWatchListEvent provides event.copy(
            onDetail = onDetail
        )
    ) {
        PullRefreshComponent(
            onRefresh = {
                viewModel.loadInit()
            }
        ) {
            WatchListContent(
                modifier = modifier.padding(top = paddingValues.calculateTopPadding()),
                uiState = uiState
            )
        }
    }
}

@Composable
fun WatchListContent(
    modifier: Modifier = Modifier,
    uiState: WatchListUIState = WatchListUIState()
) {
    val coroutine = rememberCoroutineScope()

    var selected by remember { mutableStateOf("Semua") }
    val tabItems = WatchListTab.entries
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabItems.size }
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Watchlist",
            style = MaterialTheme.typography.titleLarge,
            color = Blue500
        )

        TabRow(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        ) {
            tabItems.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutine.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(8.dp)
                ) {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = if (pagerState.currentPage != index) Blue500 else
                                MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val items = listOf("Semua", "Beli", "Jual", "Hold")

            items.forEachIndexed { index, item ->
                SelectableTextBorderComponent(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = if (index < items.lastIndex) 8.dp else 0.dp),
                    text = item,
                    isSelected = selected == item,
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) { page ->
            when (tabItems[page]) {
                WatchListTab.MY_LIST -> WatchListFirstTab(uiState = uiState)
                WatchListTab.aniqu_PICKS -> WatchListFirstTab(uiState = uiState)
                WatchListTab.IDEAS -> WatchListFirstTab(uiState = uiState)
            }
        }
    }
}

@Composable
fun WatchListFirstTab(
    modifier: Modifier = Modifier,
    uiState: WatchListUIState = WatchListUIState()
) {
    LazyColumn(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        items(uiState.stockRecommendation.orEmpty()) {
            WatchListStock(item = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WatchListPreview() {
    AppTheme {
        WatchListContent()
    }
}