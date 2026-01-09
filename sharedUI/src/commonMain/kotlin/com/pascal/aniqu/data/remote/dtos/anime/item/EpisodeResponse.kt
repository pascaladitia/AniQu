package com.pascal.aniqu.data.remote.dtos.anime.item

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val title: String? = null,
    val eps: Int? = null,
    val date: String? = null,
    val episodeId: String? = null,
    val href: String? = null,
    val otakudesuUrl: String? = null
)