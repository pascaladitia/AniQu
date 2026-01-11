package com.pascal.aniqu.ui.screen.detail.anime

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.aniqu.ui.screen.detail.anime.component.AnimeDetailAction
import com.pascal.aniqu.ui.screen.detail.anime.component.AnimeDetailCategory
import com.pascal.aniqu.ui.screen.detail.anime.component.AnimeDetailDescription
import com.pascal.aniqu.ui.screen.detail.anime.component.AnimeDetailEpisode
import com.pascal.aniqu.ui.screen.detail.anime.component.AnimeDetailHeader
import com.pascal.aniqu.ui.screen.detail.anime.component.AnimeDetailRecomend
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun AnimeDetailScreen(
    modifier: Modifier = Modifier,
    uiState: AnimeDetailUIState = AnimeDetailUIState()
) {
    LazyColumn (
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            AnimeDetailHeader(
                uiState = uiState
            )
        }

        item {
            AnimeDetailAction(
                uiState = uiState
            )
        }

        item {
            AnimeDetailDescription(
                uiState = uiState
            )
        }

        item {
            AnimeDetailEpisode(
                uiState = uiState
            ) {

            }
        }

        item {
            AnimeDetailCategory(
                uiState = uiState
            )
        }

        item {
            AnimeDetailRecomend(
                uiState = uiState
            )
        }
    }
}

@Preview
@Composable
private fun AnimeDetailPreview() {
    AppTheme {
        AnimeDetailScreen()
    }
}