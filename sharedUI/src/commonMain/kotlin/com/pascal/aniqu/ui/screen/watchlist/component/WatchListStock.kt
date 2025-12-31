package com.pascal.aniqu.ui.screen.watchlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.domain.model.StockRecommendation
import com.pascal.aniqu.ui.component.button.ButtonComponent
import com.pascal.aniqu.ui.theme.Blue500
import com.pascal.aniqu.ui.theme.Green500
import com.pascal.aniqu.ui.theme.Red500
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WatchListStock(
    modifier: Modifier = Modifier,
    item: StockRecommendation
) {

    val changeColor = when {
        item.change.contains("-") -> Red500
        item.change.contains("+") -> Green500
        else -> MaterialTheme.colorScheme.onSurface
    }

    val isSell = item.recommendation.equals("Jual", ignoreCase = true)
    val buttonColor = if (isSell) Red500 else Blue500

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.code,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = item.priceNow,
                    style = MaterialTheme.typography.bodyMedium,
                    color = changeColor
                )

                Text(
                    text = "(${item.change})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = changeColor
                )
            }

            Column(
                modifier = Modifier
                    .weight(2.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = item.recommendation,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = item.buyRange ?: item.sellPrice ?: "-",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (!item.targetPrice.isNullOrEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Target Price",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = item.targetPrice!!,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = "(${item.targetGain ?: "-"})",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Green500
                        )
                    }
                }

                if (!item.stopLoss.isNullOrEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Stop Loss",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = item.stopLoss!!,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = "(${item.stopLossPercent ?: "-"})",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Red500
                        )
                    }
                }

                if (!item.returnValue.isNullOrEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Return",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = item.returnValue,
                            style = MaterialTheme.typography.titleSmall,
                            color = if (item.returnValue.contains("-")) Red500 else Green500
                        )
                    }
                }

                if (!item.note.isNullOrEmpty()) {
                    Text(
                        text = "item.note",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            ButtonComponent(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                text = item.recommendation,
                color = buttonColor,
                onClick = {}
            )
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWatchListStock() {
    WatchListStock(
        item = StockRecommendation(
            code = "IMPC",
            priceNow = "3.180",
            change = "+4,09%",
            recommendation = "Beli",
            buyRange = "2.300 - 2.430",
            targetPrice = "3.540",
            targetGain = "49,7%",
            stopLoss = "2.080",
            stopLossPercent = "-12,1%",
            sellPrice = null,
            note = null,
            returnValue = null,
            status = null
        )
    )
}
