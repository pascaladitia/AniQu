package com.pascal.aniqu.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.label_description_onboarding
import aniqu.sharedui.generated.resources.label_get_started
import aniqu.sharedui.generated.resources.label_title_onboarding
import aniqu.sharedui.generated.resources.no_thumbnail
import com.pascal.aniqu.ui.component.button.ButtonComponent
import com.pascal.aniqu.ui.screen.onboarding.component.PagerIndicator
import com.pascal.aniqu.ui.screen.onboarding.state.LocalOnboardingEvent
import com.pascal.aniqu.ui.theme.AppTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier
) {
    val event = LocalOnboardingEvent.current

    val imageList = listOf(
        Res.drawable.no_thumbnail,
        Res.drawable.no_thumbnail,
        Res.drawable.no_thumbnail
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { imageList.size }
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(10000)

            if (!pagerState.isScrollInProgress) {
                val nextPage =
                    if (pagerState.currentPage == pagerState.pageCount - 1) {
                        0
                    } else {
                        pagerState.currentPage + 1
                    }

                pagerState.animateScrollToPage(
                    page = nextPage,
                    pageOffsetFraction = 0f
                )
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(imageList[page]),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black,
                            Color.Black.copy(alpha = 0.8f),
                            Color.Transparent
                        ),
                        startY = Float.POSITIVE_INFINITY,
                        endY = 0f
                    )
                )
                .padding(24.dp)
        ) {
            Text(
                text = stringResource(Res.string.label_title_onboarding),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.label_description_onboarding),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            PagerIndicator(
                pageCount = imageList.size,
                currentPage = pagerState.currentPage
            )

            Spacer(modifier = Modifier.height(32.dp))

            ButtonComponent(
                text = stringResource(Res.string.label_get_started),
                height = 56.dp,
                onClick = event.onNext
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    AppTheme {
        OnboardingScreen()
    }
}