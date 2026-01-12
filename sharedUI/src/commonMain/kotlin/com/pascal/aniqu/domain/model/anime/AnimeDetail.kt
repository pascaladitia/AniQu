package com.pascal.aniqu.domain.model.anime

data class AnimeDetail(
    val title: String,
    val poster: String,
    val synopsis: String,
    val rating: String,
    val batchLink: String,

    val alternativeTitle: String,
    val type: String,
    val totalEpisodes: Int,
    val score: String,
    val status: String,
    val studio: String,
    val released: String,
    val season: String,

    val genres: List<String>,
    val episodes: List<Episode>
)

data class Episode(
    val title: String,
    val episode: String,
    val date: String,
    val slug: String
)
