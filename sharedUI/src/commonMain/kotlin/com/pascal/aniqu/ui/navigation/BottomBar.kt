package com.pascal.aniqu.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pascal.aniqu.ui.navigation.screen.AnimeScreen

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val navigationItems = listOf(
                NavigationItem(
                    title = "Home",
                    iconFilled = Icons.Filled.Home,
                    iconOutlined = Icons.Outlined.Home,
                    animeScreen = AnimeScreen.HomeScreen
                ),
                NavigationItem(
                    title = "Manga",
                    iconFilled = Icons.AutoMirrored.Filled.MenuBook,
                    iconOutlined = Icons.AutoMirrored.Outlined.MenuBook,
                    animeScreen = AnimeScreen.MangaScreen
                ),
                NavigationItem(
                    title = "Search",
                    iconFilled = Icons.Filled.Search,
                    iconOutlined = Icons.Outlined.Search,
                    animeScreen = AnimeScreen.SearchScreen
                ),
                NavigationItem(
                    title = "Favorite",
                    iconFilled = Icons.Filled.Favorite,
                    iconOutlined = Icons.Outlined.Favorite,
                    animeScreen = AnimeScreen.FavoriteScreen
                ),
                NavigationItem(
                    title = "Profile",
                    iconFilled = Icons.Filled.Person,
                    iconOutlined = Icons.Outlined.Person,
                    animeScreen = AnimeScreen.ProfileScreen
                )
            )

            navigationItems.forEach { item ->
                val selected = currentRoute == item.animeScreen.route
                NavigationBarItem(
                    icon = {
                        val iconSize = if (selected) 26.dp else 24.dp
                        Icon(
                            imageVector = if (selected) item.iconFilled else item.iconOutlined,
                            contentDescription = item.title,
                            modifier = Modifier.size(iconSize),
                            tint = if (selected) MaterialTheme.colorScheme.onSurface else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontSize = 10.sp
                            ),
                            color = if (selected) MaterialTheme.colorScheme.onSurface else Color.Gray
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onSurface,
                        indicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    ),
                    alwaysShowLabel = false,
                    selected = selected,
                    onClick = {
                        navController.navigate(item.animeScreen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
