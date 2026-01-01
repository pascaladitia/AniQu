package com.pascal.aniqu.ui.navigation

sealed class Screen(val route: String) {
    data object SplashScreen: Screen("splash")
    data object OnboardingScreen: Screen("onboarding")
    data object HomeScreen: Screen("home")
    data object WatchlistScreen: Screen("watchlist")
    data object SearchScreen: Screen("search")
    data object OrderScreen: Screen("order")
    data object PortofolioScreen: Screen("portofolio")
    data object DetailScreen: Screen("detail")
    data object BookmarkScreen: Screen("bookmark")
}