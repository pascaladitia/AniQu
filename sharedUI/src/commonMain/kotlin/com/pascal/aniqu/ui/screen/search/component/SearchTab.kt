package com.pascal.aniqu.ui.screen.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.core.widgets.analyzer.RunGroup.Companion.index
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.screen.search.state.LocalSearchEvent
import com.pascal.aniqu.ui.screen.search.state.SearchUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun SearchTab(
    modifier: Modifier = Modifier,
    uiState: SearchUIState = SearchUIState()
) {
    val event = LocalSearchEvent.current
    var selectedTab by rememberSaveable { mutableStateOf(0) }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        if (uiState.isLoading && uiState.genreList.isEmpty()) {
            item {
                repeat(4) {
                    Spacer(
                        modifier = modifier
                            .padding(end = 8.dp)
                            .width(100.dp)
                            .height(42.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .shimmer()
                    )
                }
            }
        } else {
            itemsIndexed(uiState.genreList) { index, item ->
                SearchTabItem(
                    index = index,
                    label = item.title,
                    isSelected = selectedTab == index,
                    onClick = {
                        selectedTab = it
                        event.onGenre(item.genreId)
                    }
                )
            }
        }
    }
}

@Composable
fun SearchTabItem(
    modifier: Modifier = Modifier,
    index: Int,
    label: String,
    isSelected: Boolean,
    onClick: (Int) -> Unit
) {
    val colorBg = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(index) }
            .background(colorBg)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.White
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTabPreview() {
    AppTheme {
        SearchTab()
    }
}