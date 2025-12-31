package com.pascal.aniqu

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.room.RoomDatabase
import com.pascal.aniqu.data.local.database.AppDatabase
import com.pascal.aniqu.ui.navigation.RouteScreen
import com.pascal.aniqu.ui.theme.AppTheme
import com.russhwolf.settings.Settings

@Preview
@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    RouteScreen()
}

expect fun createSettings(): Settings

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
