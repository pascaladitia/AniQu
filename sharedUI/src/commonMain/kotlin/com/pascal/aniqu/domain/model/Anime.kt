package com.pascal.aniqu.domain.model

data class Anime(
    val id: String,
    val type: String,
    val title: AnimeTitle?,
    val synopsis: String?,
    val description: String?,
    val images: AnimeImages?,
    val rating: AnimeRating?,
    val episode: AnimeEpisode?,
    val status: AnimeStatus?,
    val dates: AnimeDates?,
    val isNsfw: Boolean
)

data class AnimeTitle(
    val canonical: String,
    val english: String?,
    val japanese: String?
)

data class AnimeImages(
    val poster: String?,
    val cover: String?
)

data class AnimeRating(
    val average: Double?,
    val rank: Int?,
    val popularityRank: Int?
)

data class AnimeEpisode(
    val count: Int?,
    val length: Int?,
    val totalLength: Int?
)

data class AnimeStatus(
    val status: String,
    val subtype: String,
    val ageRating: String?
)

data class AnimeDates(
    val startDate: String?,
    val endDate: String?
)
