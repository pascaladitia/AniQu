package com.pascal.aniqu.ui.screen.home.detail.anime

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.aniqu.ui.screen.home.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun AnimeDetailScreen(
    modifier: Modifier = Modifier,
    uiState: AnimeDetailUIState = AnimeDetailUIState()
) {

}

@Preview
@Composable
private fun AnimeDetailPreview() {
    AppTheme {
        AnimeDetailScreen()
    }
}