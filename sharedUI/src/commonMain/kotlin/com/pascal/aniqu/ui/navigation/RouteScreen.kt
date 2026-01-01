@file:OptIn(
    ExperimentalMotionApi::class,
    ExperimentalSharedTransitionApi::class
)

package com.pascal.aniqu.ui.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pascal.aniqu.ui.screen.bookmark.BookmarkScreen
import com.pascal.aniqu.ui.screen.detail.DetailScreen
import com.pascal.aniqu.ui.screen.favorite.FavoriteScreen
import com.pascal.aniqu.ui.screen.home.HomeScreen
import com.pascal.aniqu.ui.screen.onboarding.OnboardingRoute
import com.pascal.aniqu.ui.screen.profile.PortofolioScreen
import com.pascal.aniqu.ui.screen.splash.SplashRoute
import com.pascal.aniqu.ui.screen.watchlist.WatchListScreen
import com.pascal.aniqu.utils.base.getFromPreviousBackStack
import com.pascal.aniqu.utils.base.saveToCurrentBackStack

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RouteScreen(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    Screen.HomeScreen.route,
                    Screen.WatchlistScreen.route,
                    Screen.SearchScreen.route,
                    Screen.OrderScreen.route,
                    Screen.PortofolioScreen.route
                )) {
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        SharedTransitionLayout {
            val sharedScope: SharedTransitionScope = this

            NavHost(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                navController = navController,
                startDestination = Screen.SplashScreen.route,
            ) {
                composable(route = Screen.SplashScreen.route) {
                    SplashRoute(
                        paddingValues = paddingValues
                    ) {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
                composable(route = Screen.OnboardingScreen.route) {
                    OnboardingRoute(
                        onNext = {

                        }
                    )
                }
                composable(route = Screen.HomeScreen.route) {
                    val animScope: AnimatedVisibilityScope = this
                    HomeScreen(
                        sharedTransitionScope = sharedScope,
                        animatedVisibilityScope = animScope,
                        onDetail = {
                            saveToCurrentBackStack(navController, "articles", it)
                            navController.navigate(Screen.DetailScreen.route)
                        }
                    )
                }
                composable(route = Screen.DetailScreen.route) {
                    val animScope: AnimatedVisibilityScope = this
                    DetailScreen(
                        sharedTransitionScope = sharedScope,
                        animatedVisibilityScope = animScope,
                        item = getFromPreviousBackStack(navController, "articles"),
                        onNavBack = {
                            navController.popBackStack()
                        }
                    )
                }
                composable(route = Screen.WatchlistScreen.route) {
                    WatchListScreen(
                        paddingValues = paddingValues,
                        onDetail = {}
                    )
                }
                composable(route = Screen.SearchScreen.route) {
                    FavoriteScreen(
                        paddingValues = paddingValues,
                        onDetail = {}
                    )
                }
                composable(route = Screen.OrderScreen.route) {
                    FavoriteScreen(
                        paddingValues = paddingValues,
                        onDetail = {}
                    )
                }
                composable(route = Screen.PortofolioScreen.route) {
                    PortofolioScreen(
                        onBookMark = {
                            navController.navigate(Screen.BookmarkScreen.route)
                        },
                    )
                }
                composable(route = Screen.BookmarkScreen.route) {
                    BookmarkScreen(
                        onNavBack = {
                            navController.popBackStack()
                        },
                        onDetail = {}
                    )
                }
            }
        }
    }
}