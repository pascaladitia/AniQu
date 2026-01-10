package com.pascal.aniqu.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.screen.search.state.LocalSearchEvent
import com.pascal.aniqu.ui.screen.search.state.SearchUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUIState = SearchUIState()
) {
    val event = LocalSearchEvent.current

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {

        }
    }
}

@Preview
@Composable
fun SearchPreview() {
    AppTheme {
        SearchScreen()
    }
}

