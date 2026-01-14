package com.pascal.aniqu.ui.screen.detail.anime.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.label_download
import aniqu.sharedui.generated.resources.label_favorite
import aniqu.sharedui.generated.resources.label_share
import com.pascal.aniqu.ui.component.screenUtils.AnchoredPopup
import com.pascal.aniqu.ui.screen.detail.anime.state.AnimeDetailUIState
import com.pascal.aniqu.ui.screen.detail.anime.state.LocalAnimeDetailEvent
import com.pascal.aniqu.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimeDetailAction(
    modifier: Modifier = Modifier,
    uiState: AnimeDetailUIState = AnimeDetailUIState()
) {
    val event = LocalAnimeDetailEvent.current

    val iconFav = if (uiState.isFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimeDetailActionItem(
            icon = iconFav,
            label = stringResource(Res.string.label_favorite),
            onClick = {
                event.onFavorite(uiState.animeDetail)
            }
        )

        Spacer(Modifier.width(32.dp))

        AnchoredPopup(
            anchor = { onClick ->
                AnimeDetailActionItem(
                    icon = Icons.Default.Download,
                    label = stringResource(Res.string.label_download),
                    onClick = onClick
                )
            },
            popupContent = { close ->
                Row {
                    uiState.streamList.forEachIndexed { index, item ->
                        if (index != 0) {
                            Spacer(Modifier.width(12.dp))
                        }

                        DownloadItem(
                            index = index,
                            value = item.server,
                            isSelect = true,
                            onClick = {
                                event.onDownloadSelected(item)
                                close()
                            }
                        )
                    }
                }
            }
        )

        Spacer(Modifier.width(32.dp))

        AnimeDetailActionItem(
            icon = Icons.Default.Share,
            label = stringResource(Res.string.label_share),
            onClick = {

            }
        )
    }
}

@Composable
fun AnimeDetailActionItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .clickable { onClick() },
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Composable
fun DownloadItem(
    modifier: Modifier = Modifier,
    index: Int,
    value: String,
    isSelect: Boolean = false,
    onClick: (Int) -> Unit
) {
    val bgColor = if (isSelect) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(index) }
            .background(bgColor)
            .padding(8.dp)
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Preview
@Composable
private fun AnimeDetailActionPreview() {
    AppTheme {
        AnimeDetailAction()
    }
}