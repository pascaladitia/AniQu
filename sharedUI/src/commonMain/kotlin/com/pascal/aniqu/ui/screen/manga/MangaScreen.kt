package com.pascal.aniqu.ui.screen.manga

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pascal.aniqu.ui.screen.manga.state.MangaUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun MangaScreen(
    modifier: Modifier = Modifier,
    uiState: MangaUIState = MangaUIState()
) {

}

@Preview
@Composable
private fun MangaPreview() {
    AppTheme {
        MangaScreen()
    }
}