package com.pascal.aniqu.ui.screen.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import app.cash.paging.compose.LazyPagingItems
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.utils.toEnglishDate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.LazyRowCarousel(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    animeResponse: LazyPagingItems<Anime>?,
    imageWidth: Dp = 200.dp,
    imageHeight: Dp = 300.dp,
    imageCornerRadius: Dp = 16.dp,
    dotsActiveColor: Color = MaterialTheme.colorScheme.primary,
    dotsInActiveColor: Color = Color.LightGray,
    dotsSize: Dp = 6.dp,
    onDetail: (Anime) -> Unit
) {
    if (animeResponse == null || animeResponse.itemCount == 0) {
        LazyRowShimmer(
            imageWidth = imageWidth,
            imageHeight = imageHeight
        )
        return
    }

    var currentAnime by remember { mutableStateOf<Anime?>(null) }
    var isSlide by remember { mutableStateOf(true) }

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { animeResponse.itemCount }
    )

    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        isSlide = false
        delay(250)
        currentAnime = animeResponse[pagerState.currentPage]
        isSlide = true
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(Modifier.fillMaxWidth()) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fixed(imageWidth),
                contentPadding = PaddingValues(horizontal = imageWidth / 2),
                modifier = Modifier.fillMaxWidth()
            ) { page ->

                val anime = animeResponse[page]

                val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                val scale = 0.85f + (1f - 0.85f) * (1f - pageOffset.absoluteValue)
                val zIndex = 1f - pageOffset.absoluteValue

                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .zIndex(zIndex)
                        .shadow(16.dp, spotColor = LightGray)
                        .clip(RoundedCornerShape(imageCornerRadius))
                        .clickable { anime?.let(onDetail) }
                ) {
                    DynamicAsyncImage(
                        imageUrl = anime?.images?.poster.orEmpty(),
                        contentDescription = anime?.title?.english.orEmpty(),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .width(imageWidth)
                            .height(imageHeight)
                            .sharedElement(
                                sharedContentState =
                                    rememberSharedContentState("poster_${anime?.id}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                renderInOverlayDuringTransition = true
                            )
                    )
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        AnimatedVisibility(
            visible = isSlide && currentAnime != null,
            enter = fadeIn(tween(300)) + slideInVertically(),
            exit = fadeOut(tween(300)) + slideOutVertically()
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = currentAnime?.title?.english.orEmpty(),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.height(6.dp))

        AnimatedVisibility(
            visible = isSlide && currentAnime != null,
            enter = fadeIn(tween(250)) + slideInVertically(),
            exit = fadeOut(tween(250)) + slideOutVertically()
        ) {
            Text(
                text = currentAnime?.dates?.startDate?.toEnglishDate().orEmpty(),
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(animeResponse.itemCount.coerceAtMost(5)) { index ->
                val size by animateDpAsState(
                    if (pagerState.currentPage == index)
                        dotsSize * 1.5f
                    else dotsSize,
                    label = ""
                )

                val color by animateColorAsState(
                    if (pagerState.currentPage == index)
                        dotsActiveColor
                    else dotsInActiveColor,
                    label = ""
                )

                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .size(size)
                        .clip(CircleShape)
                        .background(color)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                )
            }
        }
    }
}


@Composable
fun LazyRowShimmer(
    modifier: Modifier = Modifier,
    imageWidth: Dp = 200.dp,
    imageHeight: Dp = 300.dp,
    imageCornerRadius: Dp = 16.dp,
) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            tween(1200, easing = FastOutSlowInEasing),
            RepeatMode.Restart
        ),
        label = ""
    )

    val brush = Brush.linearGradient(
        listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.Gray.copy(alpha = 0.3f),
            Color.LightGray.copy(alpha = 0.6f)
        ),
        start = Offset(offset - 200f, 0f),
        end = Offset(offset, 0f)
    )

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { 3 }
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(imageWidth),
            contentPadding = PaddingValues(horizontal = imageWidth / 2),
            modifier = modifier.fillMaxWidth()
        ) { page ->

            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            val scale = 0.85f + (1f - 0.85f) * (1f - pageOffset.absoluteValue)

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .shadow(16.dp)
                    .clip(RoundedCornerShape(imageCornerRadius))
                    .background(brush)
                    .width(imageWidth)
                    .height(imageHeight)
            )
        }

        Spacer(Modifier.height(16.dp))
        ShimmerBar(160.dp, 28.dp, brush)
        Spacer(Modifier.height(10.dp))
        ShimmerBar(100.dp, 18.dp, brush)
    }
}

@Composable
private fun ShimmerBar(
    width: Dp,
    height: Dp,
    brush: Brush,
    cornerRadius: Dp = 16.dp
) {
    Spacer(
        modifier = Modifier
            .clip(RoundedCornerShape(cornerRadius))
            .width(width)
            .height(height)
            .background(brush)
    )
}





