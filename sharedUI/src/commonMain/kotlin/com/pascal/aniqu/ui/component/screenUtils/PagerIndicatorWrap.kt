package com.pascal.aniqu.ui.component.screenUtils

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicatorWrap(
    pageCount: Int,
    currentPage: Int,
    maxDots: Int = 3,
    onDotClick: (Int) -> Unit
) {
    val windowStart = remember(currentPage) {
        (currentPage / maxDots) * maxDots
    }

    val dotsInWindow = minOf(
        maxDots,
        pageCount - windowStart
    )

    val activeDot = currentPage - windowStart

    Row {
        repeat(dotsInWindow) { index ->
            val isActive = index == activeDot

            val width by animateDpAsState(
                targetValue = if (isActive) 24.dp else 6.dp,
                label = ""
            )

            val color by animateColorAsState(
                targetValue = if (isActive)
                    MaterialTheme.colorScheme.primary
                else Color.LightGray,
                label = ""
            )

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .width(width)
                    .height(6.dp)
                    .clip(CircleShape)
                    .background(color)
                    .clickable {
                        onDotClick(windowStart + index)
                    }
            )
        }
    }
}
