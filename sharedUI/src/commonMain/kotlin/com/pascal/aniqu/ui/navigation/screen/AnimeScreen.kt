package com.pascal.aniqu.ui.navigation.screen

sealed class AnimeScreen(val route: String) {
    data object SplashScreen: AnimeScreen("splash")
    data object OnboardingScreen: AnimeScreen("onboarding")
    data object HomeScreen: AnimeScreen("home")
    data object MangaScreen: AnimeScreen("manga")
    data object SearchScreen: AnimeScreen("search")
    data object FavoriteScreen: AnimeScreen("favorite")
    data object ProfileScreen: AnimeScreen("profile")
    data object AnimeDetailScreen: AnimeScreen("anime_detail")
}