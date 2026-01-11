package com.pascal.aniqu.data.remote.dtos.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeDetailResponse(
    val title: String? = null,
    val poster: String? = null,
    val synopsis: String? = null,
    val rating: String? = null,
    val batch_link: String? = null,
    val info: AnimeInfoResponse? = null,
    val genres: List<String>? = null,
    val episodes: List<EpisodeResponse>? = null
)

@Serializable
data class AnimeInfoResponse(
    val alternatif: String? = null,
    val tipe: String? = null,
    val jumlah_episode: String? = null,
    val skor_anime: String? = null,
    val genre: String? = null,
    val status: String? = null,
    val studio: String? = null,
    val dirilis: String? = null,
    val musim: String? = null
)

@Serializable
data class EpisodeResponse(
    val title: String? = null,
    val episode: String? = null,
    val date: String? = null,
    val slug: String? = null
)