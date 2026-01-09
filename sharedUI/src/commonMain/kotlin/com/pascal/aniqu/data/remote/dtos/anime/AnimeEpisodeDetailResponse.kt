package com.pascal.aniqu.data.remote.dtos.anime

import com.pascal.aniqu.data.remote.dtos.anime.item.EpisodeResponse
import com.pascal.aniqu.data.remote.dtos.anime.item.GenreResponse
import kotlinx.serialization.Serializable

@Serializable
data class AnimeEpisodeDetailResponse(
    val title: String? = null,
    val animeId: String? = null,
    val releaseTime: String? = null,
    val defaultStreamingUrl: String? = null,
    val hasPrevEpisode: Boolean? = null,
    val prevEpisode: PrevNextEpisodeResponse? = null,
    val hasNextEpisode: Boolean? = null,
    val nextEpisode: PrevNextEpisodeResponse? = null,
    val server: StreamingServerResponse? = null,
    val downloadUrl: DownloadUrlResponse? = null,
    val info: EpisodeInfoResponse? = null
)

@Serializable
data class PrevNextEpisodeResponse(
    val title: String? = null,
    val episodeId: String? = null,
    val href: String? = null,
    val otakudesuUrl: String? = null
)

@Serializable
data class StreamingServerResponse(
    val qualities: List<StreamingQualityResponse>? = null
)

@Serializable
data class StreamingQualityResponse(
    val title: String? = null,
    val serverList: List<StreamingServerItemResponse>? = null
)

@Serializable
data class StreamingServerItemResponse(
    val title: String? = null,
    val serverId: String? = null,
    val href: String? = null
)

@Serializable
data class DownloadUrlResponse(
    val qualities: List<DownloadQualityResponse>? = null
)

@Serializable
data class DownloadQualityResponse(
    val title: String? = null,
    val size: String? = null,
    val urls: List<DownloadLinkResponse>? = null
)

@Serializable
data class DownloadLinkResponse(
    val title: String? = null,
    val url: String? = null
)

@Serializable
data class EpisodeInfoResponse(
    val credit: String? = null,
    val encoder: String? = null,
    val duration: String? = null,
    val type: String? = null,
    val genreList: List<GenreResponse>? = null,
    val episodeList: List<EpisodeResponse>? = null
)

