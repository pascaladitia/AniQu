package com.pascal.aniqu.data.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class StockRecommendationResponse(
    val stock_recommendations: List<StockRecommendationItem>
)

@Serializable
data class StockRecommendationItem(
    val code: String,
    val price_now: String,
    val change: String,
    val recommendation: String,
    val buy_range: String? = null,
    val target_price: String? = null,
    val target_gain: String? = null,
    val stop_loss: String? = null,
    val stop_loss_percent: String? = null,
    val sell_price: String? = null,
    val note: String? = null,
    val `return`: String? = null,
    val status: String? = null
)
