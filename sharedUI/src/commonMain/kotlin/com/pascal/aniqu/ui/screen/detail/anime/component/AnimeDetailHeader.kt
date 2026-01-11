package com.pascal.aniqu.ui.screen.detail.anime.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.ui.component.screenUtils.RatingScore
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.component.screenUtils.verticalFadeBackground
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun AnimeDetailHeader(
    modifier: Modifier = Modifier,
    uiState: AnimeDetailUIState = AnimeDetailUIState()
) {
    if (uiState.isLoading) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(500.dp)
                .shimmer()
        )

        return
    }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        uiState.sharedTransitionScope?.let {
            with(it) {
                DynamicAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(uiState.animeId),
                            animatedVisibilityScope = uiState.animatedVisibilityScope!!
                        ),
                    imageUrl = uiState.animeDetail?.poster.orEmpty(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalFadeBackground(
                    startColor = Color.Black.copy(0.8f),
                    isTop = false
                )
                .padding(24.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = uiState.animeDetail?.title.orEmpty(),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                maxLines = 2,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = uiState.animeDetail?.alternativeTitle.orEmpty(),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White.copy(0.8f)
                ),
                maxLines = 2,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = buildAnnotatedString {
                    val type = uiState.animeDetail?.type.orEmpty()
                    val status = uiState.animeDetail?.status.orEmpty()
                    val studio = uiState.animeDetail?.studio.orEmpty()

                    if (type.isNotBlank()) {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        ) {
                            append(type)
                        }
                    }

                    if (type.isNotBlank() && status.isNotBlank()) {
                        append("  |  ")
                    }

                    if (status.isNotBlank()) {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        ) {
                            append(status)
                        }
                    }

                    if (studio.isNotBlank()) {
                        append("  |  ")

                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        ) {
                            append(studio)
                        }
                    }
                },
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(16.dp))


            RatingScore(score = uiState.animeDetail?.score?.toDoubleOrNull() ?: 0.0)
        }
    }
}

@Preview
@Composable
private fun AnimeDetailHeaderPreview() {
    AppTheme {
        AnimeDetailHeader()
    }
}