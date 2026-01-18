package com.pascal.aniqu.ui.screen.manga

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pascal.aniqu.ui.component.form.SearchComponent
import com.pascal.aniqu.ui.screen.manga.component.MangaLiveItem
import com.pascal.aniqu.ui.screen.manga.state.LocalMangaEvent
import com.pascal.aniqu.ui.screen.manga.state.MangaUIState
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun MangaScreen(
    modifier: Modifier = Modifier,
    uiState: MangaUIState = MangaUIState()
) {
    val event = LocalMangaEvent.current

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (image, search) = createRefs()

                MangaLiveItem(
                    modifier = Modifier.constrainAs(image) {
                        top.linkTo(parent.top)
                    },
                    uiState = uiState,
                    onClick = event.onDetail
                )

                SearchComponent(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .shadow(16.dp, RoundedCornerShape(16.dp), spotColor = LightGray)
                        .constrainAs(search) {
                            centerAround(image.bottom)
                    },
                    onSearch = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun MangaPreview() {
    AppTheme {
        MangaScreen()
    }
}