package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.BatchResponse
import com.pascal.aniqu.data.remote.dtos.anime.ConnectionResponse
import com.pascal.aniqu.data.remote.dtos.anime.RecommendedAnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.SynopsisResponse
import com.pascal.aniqu.data.remote.dtos.anime.item.EpisodeResponse
import com.pascal.aniqu.domain.model.AnimeConnection
import com.pascal.aniqu.domain.model.AnimeDetail
import com.pascal.aniqu.domain.model.Batch
import com.pascal.aniqu.domain.model.RecommendedAnime
import com.pascal.aniqu.domain.model.Synopsis
import com.pascal.aniqu.domain.model.anime.Episode

fun AnimeDetailResponse.toDomain() = AnimeDetail(
    title = title.orEmpty(),
    poster = poster.orEmpty(),
    japanese = japanese.orEmpty(),
    score = score.orEmpty(),
    producers = producers.orEmpty(),
    type = type.orEmpty(),
    status = status.orEmpty(),
    episodes = episodes ?: 0,
    duration = duration.orEmpty(),
    aired = aired.orEmpty(),
    studios = studios.orEmpty(),
    batch = batch?.toDomain(),
    synopsis = synopsis?.toDomain(),
    episodesList = episodeList.orEmpty().map { it.toDomain() },
    recommendations = recommendedAnimeList.orEmpty().map { it.toDomain() }
)

fun BatchResponse.toDomain() = Batch(
    title = title.orEmpty(),
    batchId = batchId.orEmpty(),
    href = href.orEmpty(),
    url = otakudesuUrl.orEmpty()
)

fun SynopsisResponse.toDomain() = Synopsis(
    paragraphs = paragraphs.orEmpty(),
    connections = connections.orEmpty().map { it.toDomain() }
)

fun ConnectionResponse.toDomain() = AnimeConnection(
    title = title.orEmpty(),
    animeId = animeId.orEmpty(),
    href = href.orEmpty(),
    url = otakudesuUrl.orEmpty()
)

fun EpisodeResponse.toDomain() = Episode(
    title = title.orEmpty(),
    episode = eps ?: 0,
    date = date.orEmpty(),
    episodeId = episodeId.orEmpty(),
    href = href.orEmpty(),
    url = otakudesuUrl.orEmpty()
)

fun RecommendedAnimeResponse.toDomain() = RecommendedAnime(
    title = title.orEmpty(),
    poster = poster.orEmpty(),
    animeId = animeId.orEmpty(),
    href = href.orEmpty(),
    url = otakudesuUrl.orEmpty()
)
