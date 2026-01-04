@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pascal.aniqu.ui.screen.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.logo
import com.pascal.aniqu.ui.screen.home.state.HomeUIState
import com.pascal.aniqu.ui.screen.onboarding.state.LocalOnboardingEvent
import com.pascal.aniqu.ui.theme.AppTheme
import com.pascal.aniqu.utils.VideoUtils
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUIState = HomeUIState()
) {
    val event = LocalOnboardingEvent.current
    val videoList = VideoUtils.getOnboardingVideo()
    val pagerState = rememberPagerState(pageCount = { videoList.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(15000)
            if (!pagerState.isScrollInProgress) {
                val nextPage =
                    if (pagerState.currentPage == pagerState.pageCount - 1) 0
                    else pagerState.currentPage + 1
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(200.dp),
                        painter = painterResource(Res.drawable.logo),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    AppTheme {
        HomeScreen()
    }
}