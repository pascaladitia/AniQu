package com.pascal.aniqu.ui.screen.manga.component

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.ui.component.screenUtils.PagerIndicatorWrap
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.component.screenUtils.verticalFadeBackground
import com.pascal.aniqu.ui.screen.manga.state.MangaUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MangaLiveItem(
    modifier: Modifier = Modifier,
    uiState: MangaUIState = MangaUIState(),
    onClick: (String) -> Unit = {}
) {
    val items = uiState.mangaResponse?.popularToday

    if (uiState.isLoading) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
                .shimmer()
        )
        return
    }

    if (uiState.error.first || items.isNullOrEmpty()) return

    val pageCount = items.size
    val pagerState = rememberPagerState { pageCount }
    val scope = rememberCoroutineScope()

    val currentPage by remember {
        derivedStateOf { pagerState.currentPage }
    }

    LaunchedEffect(pageCount) {
        while (true) {
            delay(10_000)
            if (!pagerState.isScrollInProgress && pageCount > 1) {
                val next =
                    if (pagerState.currentPage == pageCount - 1) 0
                    else pagerState.currentPage + 1

                pagerState.animateScrollToPage(next)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val item = items[page]

            DynamicAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable { onClick(item.slug) }
                    .background(Color.LightGray),
                imageUrl = item.image,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .verticalFadeBackground(
                    startColor = Color.Black.copy(alpha = 0.8f),
                    isTop = true
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalFadeBackground(
                    startColor = Color.Black.copy(alpha = 0.8f),
                    isTop = false
                )
                .padding(horizontal = 16.dp, vertical = 42.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = items[currentPage].title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val rating = items[currentPage].rating
                val chapter = items[currentPage].latestChapter

                Text(
                    text = buildAnnotatedString {
                        if (chapter.isNotBlank()) {
                            withStyle(
                                SpanStyle(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                append(chapter)
                            }
                        }

                        if (rating.isNotBlank()) {
                            append("  |  ")
                        }
                    },
                    style = MaterialTheme.typography.titleSmall
                )

                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = rating.ifBlank { "-" },
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = Color.White
                    )
                )

                Spacer(Modifier.weight(1f))

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
