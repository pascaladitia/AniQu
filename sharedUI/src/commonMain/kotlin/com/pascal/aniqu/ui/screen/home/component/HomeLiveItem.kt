package com.pascal.aniqu.ui.screen.home.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.pascal.aniqu.domain.model.item.AnimeItem
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.component.screenUtils.verticalFadeBackground
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeLiveItem(
    modifier: Modifier = Modifier,
    animeLiveResponse: LazyPagingItems<AnimeItem>?,
    onClick: (String) -> Unit = {}
) {
    if (animeLiveResponse == null) return

    val loadState = animeLiveResponse.loadState

    if (loadState.refresh is LoadState.Loading) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
                .shimmer()
        )
        return
    }

    if (loadState.refresh is LoadState.Error) return

    val pageCount = animeLiveResponse.itemCount
    if (pageCount == 0) return

    val pagerState = rememberPagerState { pageCount }
    val scope = rememberCoroutineScope()

    val currentPage by remember {
        derivedStateOf { pagerState.currentPage }
    }

    LaunchedEffect(pageCount) {
        while (true) {
            delay(10000)
            if (!pagerState.isScrollInProgress && pageCount > 1) {
                val next =
                    if (pagerState.currentPage == pageCount - 1) 0
                    else pagerState.currentPage + 1

                pagerState.animateScrollToPage(next)
            }
        }
    }

    Box(modifier = modifier.fillMaxWidth()) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val item = animeLiveResponse[page] ?: return@HorizontalPager

            DynamicAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clickable { onClick(item.animeId) }
                    .background(Color.LightGray),
                imageUrl = item.poster,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .verticalFadeBackground(
                    startColor = Color.Black.copy(0.8f),
                    isTop = true
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalFadeBackground(
                    startColor = Color.Black.copy(0.8f),
                    isTop = false
                )
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = animeLiveResponse[currentPage]?.title.orEmpty(),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        val releaseDay = animeLiveResponse[currentPage]?.releaseDay.orEmpty()
                        val lastDate = animeLiveResponse[currentPage]?.latestReleaseDate.orEmpty()

                        if (releaseDay.isNotBlank()) {
                            withStyle(
                                SpanStyle(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                append(releaseDay)
                            }
                        }

                        if (releaseDay.isNotBlank() && lastDate.isNotBlank()) {
                            append("  •  ")
                        }

                        if (lastDate.isNotBlank()) {
                            withStyle(
                                SpanStyle(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                                )
                            ) {
                                append(lastDate)
                            }
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium
                )

                PagerIndicatorWrap(
                    pageCount = pageCount,
                    currentPage = currentPage,
                    maxDots = 3
                ) { targetPage ->
                    scope.launch {
                        pagerState.animateScrollToPage(targetPage)
                    }
                }
            }
        }
    }
}

@Composable
fun PagerIndicatorWrap(
    pageCount: Int,
    currentPage: Int,
    maxDots: Int = 3,
    onDotClick: (Int) -> Unit
) {
    val windowStart = remember(currentPage) {
        (currentPage / maxDots) * maxDots
    }

    val dotsInWindow = minOf(
        maxDots,
        pageCount - windowStart
    )

    val activeDot = currentPage - windowStart

    Row {
        repeat(dotsInWindow) { index ->
            val isActive = index == activeDot

            val size by animateDpAsState(
                targetValue = if (isActive) 8.dp else 6.dp,
                label = ""
            )

            val color by animateColorAsState(
                targetValue = if (isActive)
                    MaterialTheme.colorScheme.primary
                else Color.LightGray,
                label = ""
            )

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(size)
                    .clip(CircleShape)
                    .background(color)
                    .clickable {
                        onDotClick(windowStart + index)
                    }
            )
        }
    }
}

