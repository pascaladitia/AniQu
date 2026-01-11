package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.anime.AnimeGenreResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeItemResponse
import com.pascal.aniqu.domain.model.anime.AnimeGenre
import com.pascal.aniqu.domain.model.anime.AnimeItem

fun AnimeItemResponse.toDomain(): AnimeItem {
    return AnimeItem(
        slug = slug.orEmpty(),
        title = title.orEmpty(),
        poster = poster.orEmpty(),
        episodes = episodes.orEmpty(),
        type = type.orEmpty(),
        status = status.orEmpty(),
        date = date.orEmpty()
    )
}

fun AnimeGenreResponse.toDomain(): AnimeGenre {
    return AnimeGenre(
        name = name.orEmpty(),
        slug = slug.orEmpty(),
        count = count.orEmpty()
    )
}


