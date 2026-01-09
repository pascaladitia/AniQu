package com.pascal.aniqu.domain.model

import com.pascal.aniqu.domain.model.item.AnimeItem

data class Anime(
    val ongoing: AnimeSection?,
    val completed: AnimeSection?
)

data class AnimeSection(
    val href: String,
    val otakudesuUrl: String,
    val animeList: List<AnimeItem>
)