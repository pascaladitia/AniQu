package com.pascal.aniqu.domain.model

import com.pascal.aniqu.domain.model.anime.Episode

data class AnimeDetail(
    val title: String,
    val poster: String,
    val japanese: String,
    val score: String,
    val producers: String,
    val type: String,
    val status: String,
    val episodes: Int,
    val duration: String,
    val aired: String,
    val studios: String,
    val batch: Batch?,
    val synopsis: Synopsis?,
    val episodesList: List<Episode>,
    val recommendations: List<RecommendedAnime>
)

data class Batch(
    val title: String,
    val batchId: String,
    val href: String,
    val url: String
)

data class Synopsis(
    val paragraphs: List<String>,
    val connections: List<AnimeConnection>
)

data class AnimeConnection(
    val title: String,
    val animeId: String,
    val href: String,
    val url: String
)

data class RecommendedAnime(
    val title: String,
    val poster: String,
    val animeId: String,
    val href: String,
    val url: String
)
