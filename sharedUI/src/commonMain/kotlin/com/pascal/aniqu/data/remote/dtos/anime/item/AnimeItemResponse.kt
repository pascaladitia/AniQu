package com.pascal.aniqu.data.remote.dtos.anime.item

import kotlinx.serialization.Serializable

@Serializable
data class AnimeItemResponse(
    val title: String? = null,
    val poster: String? = null,
    val episodes: Int? = null,
    val releaseDay: String? = null,
    val latestReleaseDate: String? = null,
    val lastReleaseDate: String? = null,
    val score: String? = null,
    val animeId: String? = null,
    val href: String? = null,
    val studios: String? = null,
    val otakudesuUrl: String? = null,
    val genreList: List<GenreResponse>? = null
)