package com.pascal.aniqu.ui.screen.manga.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.label_new_release
import com.pascal.aniqu.ui.component.item.MangaItemComponent
import com.pascal.aniqu.ui.component.screenUtils.SectionComponent
import com.pascal.aniqu.ui.component.screenUtils.shimmer
import com.pascal.aniqu.ui.screen.manga.state.LocalMangaEvent
import com.pascal.aniqu.ui.screen.manga.state.MangaUIState

@Composable
fun MangaNewReleaseItem(
    modifier: Modifier = Modifier,
    uiState: MangaUIState = MangaUIState()
) {
    val event = LocalMangaEvent.current

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SectionComponent(
            label = Res.string.label_new_release,
            onClick = {}
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (uiState.isLoading) {
                items(3) {
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .shimmer()
                    )
                }
            } else {
                items(uiState.mangaResponse?.latestReleases.orEmpty()) {
                    MangaItemComponent(
                        item = it,
                        onClick = event.onDetail
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MangaNewReleasePreview() {
    MangaNewReleaseItem()
}