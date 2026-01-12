package com.pascal.aniqu.ui.screen.detail.anime.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun AnimeDetailDescription(
    modifier: Modifier = Modifier,
    uiState: AnimeDetailUIState = AnimeDetailUIState()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        if (uiState.isLoading) {
            listOf(
                Modifier.fillMaxWidth(),
                Modifier.fillMaxWidth(),
                Modifier.width(200.dp)
            ).forEach { modifier ->
                Box(
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .height(16.dp)
                        .shimmer()
                )
            }
        } else {
            uiState.animeDetail?.synopsis?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun AnimeDetailDescriptionPreview() {
    AppTheme {
        AnimeDetailDescription()
    }
}

