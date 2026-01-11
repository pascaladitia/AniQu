package com.pascal.aniqu.domain.model

import com.pascal.aniqu.domain.model.anime.Episode

data class AnimeEpisodeDetail(
    val title: String,
    val animeId: String,
    val releaseTime: String,
    val streamingUrl: String,
    val prevEpisode: EpisodeNav?,
    val nextEpisode: EpisodeNav?,
    val streamingQualities: List<StreamingQuality>,
    val downloadQualities: List<DownloadQuality>,
    val info: EpisodeInfo
)

data class EpisodeNav(
    val title: String,
    val episodeId: String,
    val href: String,
    val url: String
)

data class StreamingQuality(
    val quality: String,
    val servers: List<StreamingServer>
)

data class StreamingServer(
    val title: String,
    val serverId: String,
    val href: String
)

data class DownloadQuality(
    val quality: String,
    val size: String,
    val links: List<DownloadLink>
)

data class DownloadLink(
    val title: String,
    val url: String
)

data class EpisodeInfo(
    val credit: String,
    val encoder: String,
    val duration: String,
    val type: String,
    val episodes: List<Episode>
)

