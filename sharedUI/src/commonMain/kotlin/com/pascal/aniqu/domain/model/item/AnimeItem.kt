package com.pascal.aniqu.domain.model.item

data class AnimeItem(
    val title: String,
    val poster: String,
    val episodes: Int,
    val releaseDay: String,
    val latestReleaseDate: String,
    val lastReleaseDate: String,
    val score: String,
    val animeId: String,
    val href: String,
    val studios: String,
    val otakudesuUrl: String
)
