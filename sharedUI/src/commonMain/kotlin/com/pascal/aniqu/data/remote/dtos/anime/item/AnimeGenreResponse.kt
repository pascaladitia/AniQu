package com.pascal.aniqu.data.remote.dtos.anime.item

import kotlinx.serialization.Serializable

@Serializable
data class AnimeGenreResponse(
    val genreList: List<GenreResponse>? = null
)

@Serializable
data class GenreResponse(
    val title: String? = null,
    val genreId: String? = null,
    val href: String? = null,
    val otakudesuUrl: String? = null
)
