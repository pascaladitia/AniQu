package com.pascal.aniqu.ui.screen.home.component

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.domain.model.MarketHighlight
import com.pascal.aniqu.domain.model.MarketHighlightData
import com.pascal.aniqu.ui.component.button.ButtonComponent
import com.pascal.aniqu.ui.screen.home.state.HomeUIState
import com.pascal.aniqu.ui.theme.Blue500
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalSharedTransitionApi
fun LazyListScope.homeMarket(
    modifier: Modifier = Modifier,
    uiState: HomeUIState = HomeUIState()
) {
    val item = uiState.marketHighlight
    if (item == null) return

    item {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            text = item.section,
            style = MaterialTheme.typography.titleLarge,
            color = Blue500
        )
    }

    items(item.items) { data ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.outline,
                    RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = data.label,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.error
                )

                Text(
                    text = data.timeAgo ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = data.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(2.5f)
                ) {

                    val parts = data.priceInfo?.split(" ") ?: listOf("", "")
                    val stock = parts.getOrNull(0) ?: "-"
                    val price = parts.getOrNull(1) ?: ""

                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = stock,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.width(4.dp))

                        val color = when {
                            price.contains("-") -> MaterialTheme.colorScheme.error
                            price.contains("(") && price.contains("0%") -> MaterialTheme.colorScheme.inverseOnSurface
                            else -> MaterialTheme.colorScheme.primary
                        }

                        Text(
                            text = price,
                            style = MaterialTheme.typography.titleMedium,
                            color = color
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = data.companyName ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }

                ButtonComponent(
                    modifier = Modifier.weight(1f),
                    text = data.action ?: "Beli",
                    color = Blue500,
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun PreviewHomeMarket() {
    val previewData = HomeUIState(
        marketHighlight = MarketHighlight(
            section = "Market Highlight",
            items = listOf(
                MarketHighlightData(
                    label = "Company Update",
                    title = "Book building Superbank dimulai! Harga di Rp 525 - Rp 695",
                    priceInfo = "EMTK 1.255 (-3,09%)",
                    companyName = "Elang Mahkota Teknologi Tbk.",
                    timeAgo = "21 Jam lalu",
                    action = "Beli"
                )
            )
        )
    )

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        homeMarket(uiState = previewData)
    }
}
