package com.pascal.aniqu.ui.screen.detail.anime.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.VideoSettings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chaintech.videoplayer.host.MediaPlayerHost
import chaintech.videoplayer.model.ScreenResize
import chaintech.videoplayer.ui.video.VideoPlayerComposable
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.ui.screen.detail.anime.state.LocalAnimeDetailEvent
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun AnimeDetailEpisode(
    modifier: Modifier = Modifier,
    uiState: AnimeDetailUIState = AnimeDetailUIState()
) {
    val event = LocalAnimeDetailEvent.current
    var episodeSelected by remember { mutableStateOf(0) }
    var serverSelected by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
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

        key(uiState.streamingUrl) {
            val playerHost = remember(uiState.streamingUrl) {
                MediaPlayerHost(
                    mediaUrl = uiState.streamingUrl,
                    autoPlay = false,
                    isLooping = false,
                    initialVideoFitMode = ScreenResize.FILL
                )
            }

            Crossfade(
                targetState = uiState.isLoading || uiState.streamingUrl.isBlank(),
                animationSpec = tween(500)
            ) { loading ->
                if (loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .shimmer()
                    )
                } else {
                    VideoPlayerComposable(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        playerHost = playerHost
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = true
        ) {
            itemsIndexed(uiState.animeDetail?.episodes.orEmpty()) { index, item ->
                EpisodeItem(
                    index = index,
                    value = "Ep ${item.episode}",
                    isSelect = episodeSelected == index,
                    onClick = {
                        episodeSelected = it
                        event.onEpisodeSelected(item.slug)
                    }
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.VideoSettings,
                    contentDescription = null
                )
            }

            itemsIndexed(uiState.streamingList) { index, item ->
                EpisodeItem(
                    index = index,
                    value = item.resolution,
                    isSelect = serverSelected == index,
                    onClick = {
                        serverSelected = it
                        event.onServerSelected(item)
                    }
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

@Composable
fun EpisodeItem(
    modifier: Modifier = Modifier,
    index: Int,
    value: String,
    isSelect: Boolean = false,
    onClick: (Int) -> Unit
) {
    val bgColor = if (isSelect) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(index) }
            .background(bgColor)
            .padding(8.dp)
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
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