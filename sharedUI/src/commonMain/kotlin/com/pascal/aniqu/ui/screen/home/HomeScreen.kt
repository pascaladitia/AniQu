@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pascal.aniqu.ui.screen.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.label_completed
import aniqu.sharedui.generated.resources.label_ongoing
import app.cash.paging.compose.LazyPagingItems
import com.pascal.aniqu.domain.model.anime.AnimeItem
import com.pascal.aniqu.ui.component.item.AnimeItemComponent
import com.pascal.aniqu.ui.component.screenUtils.SectionComponent
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.screen.home.component.HomeLiveItem
import com.pascal.aniqu.ui.screen.home.component.LazyRowCarousel
import com.pascal.aniqu.ui.screen.home.state.HomeUIState
import com.pascal.aniqu.ui.screen.home.state.LocalHomeEvent
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUIState = HomeUIState(),
    animeLiveResponse: LazyPagingItems<AnimeItem>? = null
) {
    val event = LocalHomeEvent.current

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            HomeLiveItem(
                animeLiveResponse = animeLiveResponse,
                onClick = event.onDetail
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                SectionComponent(
                    label = Res.string.label_ongoing,
                    onClick = {}
                )

                uiState.sharedTransitionScope?.let {
                    with(it) {
                        LazyRowCarousel(
                            isLoading = uiState.isLoading,
                            items = uiState.animeList,
                            animatedVisibilityScope = uiState.animatedVisibilityScope!!
                        ) {
                            event.onDetail(it.slug)
                        }
                    }
                }
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            SectionComponent(
                label = Res.string.label_completed,
                onClick = {}
            )
        }

        if (uiState.isLoading) {
            itemsIndexed(List(2) { it }) { index, _ ->
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
            itemsIndexed(uiState.animeGenreList) { index, items ->
                AnimeItemComponent(
                    modifier = Modifier.padding(
                        start = if (index % 2 == 0) 16.dp else 8.dp,
                        end = if (index % 2 == 0) 8.dp else 16.dp
                    ),
                    items = items,
                    onClick = event.onDetail
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    AppTheme {
        HomeScreen()
    }
}