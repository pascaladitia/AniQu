package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.home.AnimeHomeResponse
import com.pascal.aniqu.data.remote.dtos.home.AnimeItemResponse
import com.pascal.aniqu.data.remote.dtos.home.AnimeSectionResponse
import com.pascal.aniqu.domain.model.AnimeHome
import com.pascal.aniqu.domain.model.AnimeItem
import com.pascal.aniqu.domain.model.AnimeSection

fun AnimeHomeResponse?.toDomain(): AnimeHome {
    return AnimeHome(
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


