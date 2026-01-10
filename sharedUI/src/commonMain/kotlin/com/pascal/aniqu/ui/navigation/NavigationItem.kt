package com.pascal.aniqu.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.pascal.aniqu.ui.navigation.screen.AnimeScreen

data class NavigationItem(
    val title: String,
    val iconFilled: ImageVector,
    val iconOutlined: ImageVector,
    val animeScreen: AnimeScreen
)
