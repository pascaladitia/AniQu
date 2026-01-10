package com.pascal.aniqu.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.component.form.SearchComponent
import com.pascal.aniqu.ui.component.item.AnimeItemComponent
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.screen.search.component.SearchTab
import com.pascal.aniqu.ui.screen.search.state.LocalSearchEvent
import com.pascal.aniqu.ui.screen.search.state.SearchUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUIState = SearchUIState()
) {
    val event = LocalSearchEvent.current
    val animeList = if (uiState.isSearch) uiState.animeList else uiState.animeByGenreList

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                SearchComponent(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    suggestions = uiState.animeByGenreList.map { it.title }
                ) {
                    event.onSearch(it)
                }

                SearchTab(
                    modifier = Modifier.padding(top = 16.dp),
                    uiState = uiState
                )
            }
        }

        if (uiState.isLoading) {
            items(4) { index ->
                Box(
                    modifier = Modifier
                        .padding(
                            start = if (index % 2 == 0) 16.dp else 8.dp,
                            end = if (index % 2 == 0) 8.dp else 16.dp
                        )
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .shimmer()
                )
            }
        } else {
            itemsIndexed(animeList) { index, items ->
                AnimeItemComponent(
                    modifier = Modifier.padding(
                        start = if (index % 2 == 0) 16.dp else 8.dp,
                        end = if (index % 2 == 0) 8.dp else 16.dp
                    ),
                    items = items
                )
            }
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

