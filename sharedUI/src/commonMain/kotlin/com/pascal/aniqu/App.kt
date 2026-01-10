package com.pascal.aniqu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.RoomDatabase
import com.pascal.aniqu.data.local.database.AppDatabase
import com.pascal.aniqu.ui.navigation.route.NavAnimeRoute
import com.pascal.aniqu.ui.theme.AppTheme
import com.pascal.aniqu.ui.theme.LocalThemeIsDark
import com.russhwolf.settings.Settings

@Preview
@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {

    var isDark by LocalThemeIsDark.current

    LaunchedEffect(Unit) {
        isDark = true
    }

    NavAnimeRoute()
}

expect fun createSettings(): Settings

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
