package com.pascal.aniqu.ui.screen.detail.anime.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun AnimeDetailEpisode(
    modifier: Modifier = Modifier,
    uiState: AnimeDetailUIState = AnimeDetailUIState(),
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Episode",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Spacer(Modifier.height(16.dp))

        Crossfade(
            targetState = uiState.isLoading,
            animationSpec = tween(durationMillis = 500)
        ) { isLoading ->
            if (isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .shimmer()
                )
            } else {
                EpisodePoster(
                    modifier = modifier,
                    imageUrl = uiState.animeDetail?.poster.orEmpty(),
                    onClick = onClick
                )
            }
        }

    }
}

@Composable
private fun EpisodePoster(
    modifier: Modifier,
    imageUrl: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        DynamicAsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() },
            imageUrl = imageUrl,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        Icon(
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.Center),
            imageVector = Icons.Default.PlayCircle,
            contentDescription = null,
            tint = Color.White
        )
    }
}


@Preview
@Composable
private fun AnimeDetailEpisodePreview() {
    AppTheme {
        AnimeDetailEpisode()
    }
}