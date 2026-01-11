package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.EpisodeResponse
import com.pascal.aniqu.domain.model.anime.AnimeDetail
import com.pascal.aniqu.domain.model.anime.Episode

fun AnimeDetailResponse.toDomain(): AnimeDetail {
    val info = info

    return AnimeDetail(
        title = title.orEmpty(),
        poster = poster.orEmpty(),
        synopsis = synopsis.orEmpty(),
        rating = rating.orEmpty(),
        batchLink = batch_link.orEmpty(),

        alternativeTitle = info?.alternatif.orEmpty(),
        type = info?.tipe.orEmpty(),
        totalEpisodes = info?.jumlah_episode?.toIntOrNull() ?: 0,
        score = info?.skor_anime.orEmpty(),
        status = info?.status.orEmpty(),
        studio = info?.studio.orEmpty(),
        released = info?.dirilis.orEmpty(),
        season = info?.musim.orEmpty(),

        genres = genres.orEmpty(),
        episodes = episodes.orEmpty().map { it.toDomain() }
    )
}

fun EpisodeResponse.toDomain() = Episode(
    title = title.orEmpty(),
    episode = episode.orEmpty(),
    date = date.orEmpty(),
    slug = slug.orEmpty()
)
