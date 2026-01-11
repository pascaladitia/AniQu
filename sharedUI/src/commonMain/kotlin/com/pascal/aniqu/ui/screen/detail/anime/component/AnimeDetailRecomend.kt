package com.pascal.aniqu.ui.screen.detail.anime.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun AnimeDetailRecomend(
    modifier: Modifier = Modifier,
    uiState: AnimeDetailUIState = AnimeDetailUIState()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Recomend to Watch",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Spacer(Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.recomendList) {
                AnimeDetailRecomendItem(
                    poster = it.poster,
                    title = it.title,
                    description = it.status.ifBlank { it.type }
                )
            }
        }

    }
}

@Composable
fun AnimeDetailRecomendItem(
    modifier: Modifier = Modifier,
    poster: String,
    title: String,
    description: String
) {
    Column(
        modifier = modifier.width(120.dp)
    ) {
        DynamicAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp)),
            imageUrl = poster,
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun AnimeDetailCategoryPreview() {
    AppTheme {
        AnimeDetailRecomend()
    }
}