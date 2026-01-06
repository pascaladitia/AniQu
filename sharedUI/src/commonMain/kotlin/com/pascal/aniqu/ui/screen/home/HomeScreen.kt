@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pascal.aniqu.ui.screen.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.ui.component.screenUtils.PagerIndicator
import com.pascal.aniqu.ui.component.screenUtils.verticalFadeBackground
import com.pascal.aniqu.ui.screen.home.component.HomeOngoingItem
import com.pascal.aniqu.ui.screen.home.component.LazyRowCarousel
import com.pascal.aniqu.ui.screen.home.state.HomeUIState
import com.pascal.aniqu.ui.screen.onboarding.state.LocalOnboardingEvent
import com.pascal.aniqu.ui.theme.AppTheme
import com.pascal.aniqu.utils.VideoUtils
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUIState = HomeUIState(),
    animeResponse: LazyPagingItems<Anime>? = null
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
                    DynamicAsyncImage(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxWidth()
                            .height(240.dp),
                        imageUrl = "",
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.6f),
                                    Color.Black.copy(alpha = 0.5f),
                                    Color.Transparent
                                ),
                                startY = Float.POSITIVE_INFINITY,
                                endY = 0f
                            )
                        )
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Text(
                        text = "New episode",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "New episode",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )

                        PagerIndicator(
                            pageCount = videoList.size,
                            currentPage = pagerState.currentPage
                        )
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Trending Now",
                    style = MaterialTheme.typography.titleLarge
                )

                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            uiState.sharedTransitionScope?.let {
                with(it) {
                    LazyRowCarousel(
                        animeResponse = animeResponse,
                        animatedVisibilityScope = uiState.animatedVisibilityScope!!
                    ) {
                        event.onNext()
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "On Going",
                    style = MaterialTheme.typography.titleLarge
                )

                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        items(videoList) {
            HomeOngoingItem()
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