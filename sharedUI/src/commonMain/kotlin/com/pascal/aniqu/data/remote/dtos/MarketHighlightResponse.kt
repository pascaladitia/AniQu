package com.pascal.aniqu.data.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class MarketHighlightResponse(
    val section: String?,
    val items: List<MarketHighlightItem>?
)

@Serializable
data class MarketHighlightItem(
    val label: String?,
    val title: String?,
    val price_info: String?,
    val company_name: String?,
    val time_ago: String?,
    val action: String?
)
