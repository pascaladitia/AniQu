package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.anime.AnimeEpisodeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse
import com.pascal.aniqu.data.remote.dtos.anime.DownloadQualityResponse
import com.pascal.aniqu.data.remote.dtos.anime.EpisodeInfoResponse
import com.pascal.aniqu.data.remote.dtos.anime.PrevNextEpisodeResponse
import com.pascal.aniqu.data.remote.dtos.anime.StreamingQualityResponse
import com.pascal.aniqu.domain.model.AnimeEpisodeDetail
import com.pascal.aniqu.domain.model.AnimeStreaming
import com.pascal.aniqu.domain.model.DownloadLink
import com.pascal.aniqu.domain.model.DownloadQuality
import com.pascal.aniqu.domain.model.EpisodeInfo
import com.pascal.aniqu.domain.model.EpisodeNav
import com.pascal.aniqu.domain.model.StreamingQuality
import com.pascal.aniqu.domain.model.StreamingServer

fun AnimeEpisodeDetailResponse.toDomain() = AnimeEpisodeDetail(
    title = title.orEmpty(),
    animeId = animeId.orEmpty(),
    releaseTime = releaseTime.orEmpty(),
    streamingUrl = defaultStreamingUrl.orEmpty(),
    prevEpisode = prevEpisode?.toDomain(),
    nextEpisode = nextEpisode?.toDomain(),
    streamingQualities = server?.qualities.orEmpty().map { it.toDomain() },
    downloadQualities = downloadUrl?.qualities.orEmpty().map { it.toDomain() },
    info = info?.toDomain() ?: EpisodeInfo(
        credit = "",
        encoder = "",
        duration = "",
        type = "",
        genres = emptyList(),
        episodes = emptyList()
    )
)

fun PrevNextEpisodeResponse.toDomain() = EpisodeNav(
    title = title.orEmpty(),
    episodeId = episodeId.orEmpty(),
    href = href.orEmpty(),
    url = otakudesuUrl.orEmpty()
)

fun StreamingQualityResponse.toDomain() = StreamingQuality(
    quality = title.orEmpty(),
    servers = serverList.orEmpty().map {
        StreamingServer(
            title = it.title.orEmpty(),
            serverId = it.serverId.orEmpty(),
            href = it.href.orEmpty()
        )
    }
)

fun DownloadQualityResponse.toDomain() = DownloadQuality(
    quality = title.orEmpty(),
    size = size.orEmpty(),
    links = urls.orEmpty().map {
        DownloadLink(
            title = it.title.orEmpty(),
            url = it.url.orEmpty()
        )
    }
)

fun EpisodeInfoResponse.toDomain() = EpisodeInfo(
    credit = credit.orEmpty(),
    encoder = encoder.orEmpty(),
    duration = duration.orEmpty(),
    type = type.orEmpty(),
    genres = genreList.orEmpty().map { it.toDomain() },
    episodes = episodeList.orEmpty().map { it.toDomain() }
)

fun AnimeStreamingResponse.toDomain() = AnimeStreaming(
    url = url.orEmpty()
)
