package com.pascal.aniqu.domain.mapper.favorite

import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.domain.model.anime.AnimeDetail
import com.pascal.aniqu.domain.model.anime.AnimeItem

fun AnimeItem.toEntity(): FavoritesEntity {
    return FavoritesEntity(
        title = title,
        slug = slug,
        poster = poster,
        episodes = episodes,
        type = type,
        status = status,
        date = date
    )
}

fun FavoritesEntity.toAnimeItem(): AnimeItem {
    return AnimeItem(
        title = title.orEmpty(),
        slug = slug.orEmpty(),
        poster = poster.orEmpty(),
        episodes = episodes.orEmpty(),
        type = type.orEmpty(),
        status = status.orEmpty(),
        date = date.orEmpty()
    )
}

fun AnimeDetail.toEntity(): FavoritesEntity {
    return FavoritesEntity(
        title = title,
        slug = slug,
        poster = poster,
        episodes = totalEpisodes.toString(),
        type = type,
        status = status,
        date = released
    )
}