package com.pascal.aniqu.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.domain.model.manga.item.MangaItem
import com.pascal.aniqu.ui.component.screenUtils.DynamicAsyncImage
import com.pascal.aniqu.ui.component.screenUtils.verticalFadeBackground
import com.pascal.aniqu.ui.theme.AppTheme

@Composable
fun MangaItemComponent(
    modifier: Modifier = Modifier,
    item: MangaItem? = null,
    onClick: (String) -> Unit = {}
) {
    if (item == null) return

    Box(
        modifier = modifier
            .width(160.dp)
            .height(240.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(item.slug) }
    ) {
        DynamicAsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray),
            imageUrl = item.image,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalFadeBackground(
                    startColor = Color.Black.copy(alpha = 0.8f),
                    isTop = false
                )
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (item.latestChapter.isNotBlank()) {
                    Text(
                        text = "${item.latestChapter}  |  ",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }

                if (item.rating.isNotBlank()) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow
                    )

                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = item.rating,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MangaItemPreview() {
    AppTheme {
        MangaItemComponent(
            item = MangaItem(
                title = "One Piece",
                slug = "one-piece",
                image = "",
                latestChapter = "Chapter 1171",
                rating = "9.3",
                link = "",
                chapters = emptyList()
            )
        )
    }
}
