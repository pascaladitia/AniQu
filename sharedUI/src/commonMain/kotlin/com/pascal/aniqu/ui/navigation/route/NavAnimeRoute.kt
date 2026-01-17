@file:OptIn(
    ExperimentalMotionApi::class,
    ExperimentalSharedTransitionApi::class
)

package com.pascal.aniqu.ui.navigation.route

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
import com.pascal.aniqu.data.preferences.PrefLogin
import com.pascal.aniqu.domain.model.anime.Stream
import com.pascal.aniqu.ui.navigation.BottomBar
import com.pascal.aniqu.ui.navigation.screen.AnimeScreen
import com.pascal.aniqu.ui.screen.detail.anime.AnimeDetailRoute
import com.pascal.aniqu.ui.screen.detail.streaming.AnimeStreamingRoute
import com.pascal.aniqu.ui.screen.favorite.FavoriteRoute
import com.pascal.aniqu.ui.screen.home.HomeRoute
import com.pascal.aniqu.ui.screen.manga.MangaRoute
import com.pascal.aniqu.ui.screen.onboarding.OnboardingRoute
import com.pascal.aniqu.ui.screen.profile.ProfileRoute
import com.pascal.aniqu.ui.screen.search.SearchRoute
import com.pascal.aniqu.ui.screen.splash.SplashRoute
import com.pascal.aniqu.utils.base.getFromPreviousBackStack
import com.pascal.aniqu.utils.base.saveToCurrentBackStack

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavAnimeRoute(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    AnimeScreen.HomeScreen.route,
                    AnimeScreen.MangaScreen.route,
                    AnimeScreen.SearchScreen.route,
                    AnimeScreen.FavoriteScreen.route,
                    AnimeScreen.ProfileScreen.route
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
                startDestination = AnimeScreen.SplashScreen.route,
            ) {
                composable(route = AnimeScreen.SplashScreen.route) {
                    SplashRoute(
                        paddingValues = paddingValues
                    ) {
                        val route = if (PrefLogin.getIsOnboarding()) {
                            AnimeScreen.HomeScreen.route
                        } else {
                            AnimeScreen.OnboardingScreen.route
                        }

                        navController.navigate(route) {
                            popUpTo(AnimeScreen.SplashScreen.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
                composable(route = AnimeScreen.OnboardingScreen.route) {
                    OnboardingRoute(
                        onNext = {
                            navController.navigate(AnimeScreen.HomeScreen.route) {
                                popUpTo(AnimeScreen.OnboardingScreen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
                composable(route = AnimeScreen.HomeScreen.route) {
                    val animScope: AnimatedVisibilityScope = this
                    HomeRoute(
                        sharedTransitionScope = sharedScope,
                        animatedVisibilityScope = animScope,
                        onDetail = {
                            saveToCurrentBackStack(navController, NavKey.SLUG, it)
                            navController.navigate(AnimeScreen.AnimeDetailScreen.route)
                        }
                    )
                }
                composable(route = AnimeScreen.MangaScreen.route) {
                    MangaRoute(
                        paddingValues = paddingValues,
                        onDetail = {}
                    )
                }
                composable(route = AnimeScreen.SearchScreen.route) {
                    SearchRoute(
                        onDetail = {
                            saveToCurrentBackStack(navController, NavKey.SLUG, it)
                            navController.navigate(AnimeScreen.AnimeDetailScreen.route)
                        }
                    )
                }
                composable(route = AnimeScreen.FavoriteScreen.route) {
                    FavoriteRoute(
                        onDetail = {
                            saveToCurrentBackStack(navController, NavKey.SLUG, it)
                            navController.navigate(AnimeScreen.AnimeDetailScreen.route)
                        }
                    )
                }
                composable(route = AnimeScreen.ProfileScreen.route) {
                    ProfileRoute(
                        onBookMark = {
                            navController.navigate(AnimeScreen.ProfileScreen.route)
                        },
                    )
                }
                composable(route = AnimeScreen.AnimeDetailScreen.route) {
                    val animScope: AnimatedVisibilityScope = this

                    AnimeDetailRoute(
                        sharedTransitionScope = sharedScope,
                        animatedVisibilityScope = animScope,
                        slug = getFromPreviousBackStack<String>(navController, NavKey.SLUG),
                        onNavPlayStream = {
                            saveToCurrentBackStack(navController, NavKey.STREAM, it)
                            navController.navigate(AnimeScreen.AnimeStreamingScreen.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onNavBack = {
                            navController.popBackStack()
                        }
                    )
                }
                composable(route = AnimeScreen.AnimeStreamingScreen.route) {
                    AnimeStreamingRoute(
                        stream = getFromPreviousBackStack<Stream>(navController, NavKey.STREAM),
                        onNavBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}