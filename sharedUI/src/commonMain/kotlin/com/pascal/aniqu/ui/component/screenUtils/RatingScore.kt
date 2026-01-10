package com.pascal.aniqu.ui.component.screenUtils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingScore(
    score: Double,
    modifier: Modifier = Modifier,
    maxScore: Double = 10.0,
    starCount: Int = 5,
    starSize: Dp = 20.dp,
    space: Dp = 4.dp
) {
    val normalizedScore = (score / maxScore) * starCount
    val fullStars = normalizedScore.toInt()
    val hasHalfStar = (normalizedScore - fullStars) >= 0.5

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(starCount) { index ->
            val icon = when {
                index < fullStars -> Icons.Filled.Star
                index == fullStars && hasHalfStar -> Icons.AutoMirrored.Filled.StarHalf
                else -> Icons.Outlined.Star
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(starSize)
            )

            if (index != starCount - 1) {
                Spacer(modifier = Modifier.width(space))
            }
        }
    }
}
