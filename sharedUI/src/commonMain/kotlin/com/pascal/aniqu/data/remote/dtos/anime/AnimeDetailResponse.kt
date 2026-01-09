package com.pascal.aniqu.data.remote.dtos.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeDetailResponse(
    val title: String? = null,
    val poster: String? = null,
    val japanese: String? = null,
    val score: String? = null,
    val producers: String? = null,
    val type: String? = null,
    val status: String? = null,
    val episodes: Int? = null,
    val duration: String? = null,
    val aired: String? = null,
    val studios: String? = null,
    val batch: BatchResponse? = null,
    val synopsis: SynopsisResponse? = null,
    val genreList: List<GenreResponse>? = null,
    val episodeList: List<EpisodeResponse>? = null,
    val recommendedAnimeList: List<RecommendedAnimeResponse>? = null
)

@Serializable
data class BatchResponse(
    val title: String? = null,
    val batchId: String? = null,
    val href: String? = null,
    val otakudesuUrl: String? = null
)

@Serializable
data class SynopsisResponse(
    val paragraphs: List<String>? = null,
    val connections: List<ConnectionResponse>? = null
)

@Serializable
data class ConnectionResponse(
    val title: String? = null,
    val animeId: String? = null,
    val href: String? = null,
    val otakudesuUrl: String? = null
)

@Serializable
data class GenreResponse(
    val title: String? = null,
    val genreId: String? = null,
    val href: String? = null,
    val otakudesuUrl: String? = null
)

@Serializable
data class EpisodeResponse(
    val title: String? = null,
    val eps: Int? = null,
    val date: String? = null,
    val episodeId: String? = null,
    val href: String? = null,
    val otakudesuUrl: String? = null
)

@Serializable
data class RecommendedAnimeResponse(
    val title: String? = null,
    val poster: String? = null,
    val animeId: String? = null,
    val href: String? = null,
    val otakudesuUrl: String? = null
)
