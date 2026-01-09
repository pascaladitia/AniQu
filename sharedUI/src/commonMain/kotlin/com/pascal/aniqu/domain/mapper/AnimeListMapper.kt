package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse
import com.pascal.aniqu.data.remote.dtos.anime.item.AnimeItemResponse
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.model.AnimeSection
import com.pascal.aniqu.domain.model.item.AnimeItem

fun AnimeResponse?.toDomain(): Anime {
    return Anime(
        ongoing = this?.ongoing?.toDomain(),
        completed = this?.completed?.toDomain()
    )
}

fun AnimeSectionResponse.toDomain(): AnimeSection {
    return AnimeSection(
        href = href.orEmpty(),
        otakudesuUrl = otakudesuUrl.orEmpty(),
        animeList = animeList.map { it.toDomain() }
    )
}

fun AnimeItemResponse.toDomain(): AnimeItem {
    return AnimeItem(
        title = title.orEmpty(),
        poster = poster.orEmpty(),
        episodes = episodes ?: 0,
        releaseDay = releaseDay.orEmpty(),
        latestReleaseDate = latestReleaseDate.orEmpty(),
        lastReleaseDate = lastReleaseDate.orEmpty(),
        score = score.orEmpty(),
        animeId = animeId.orEmpty(),
        href = href.orEmpty(),
        otakudesuUrl = otakudesuUrl.orEmpty()
    )
}


