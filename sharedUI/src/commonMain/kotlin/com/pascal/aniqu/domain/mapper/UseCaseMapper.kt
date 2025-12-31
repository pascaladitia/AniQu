package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.MarketHighlightResponse
import com.pascal.aniqu.data.remote.dtos.StockRecommendationResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.DashboardResponse
import com.pascal.aniqu.domain.model.Dashboard
import com.pascal.aniqu.domain.model.MarketHighlight
import com.pascal.aniqu.domain.model.MarketHighlightData
import com.pascal.aniqu.domain.model.StockRecommendation

fun DashboardResponse.toDomain(): Dashboard {
    return Dashboard(
        code = this.code ?: 0,
        success = this.success ?: false,
        message = this.message.orEmpty()
    )
}

fun MarketHighlightResponse.toDomain(): MarketHighlight {
    return MarketHighlight(
        section = this.section.orEmpty(),
        items = this.items?.map {
            MarketHighlightData(
                label = it.label.orEmpty(),
                title = it.title.orEmpty(),
                priceInfo = it.price_info,
                companyName = it.company_name,
                timeAgo = it.time_ago,
                action = it.action
            )
        }.orEmpty()
    )
}

fun StockRecommendationResponse.toDomain(): List<StockRecommendation> {
    return stock_recommendations.map { item ->
        StockRecommendation(
            code = item.code,
            priceNow = item.price_now,
            change = item.change,
            recommendation = item.recommendation,
            buyRange = item.buy_range,
            targetPrice = item.target_price,
            targetGain = item.target_gain,
            stopLoss = item.stop_loss,
            stopLossPercent = item.stop_loss_percent,
            sellPrice = item.sell_price,
            note = item.note,
            returnValue = item.`return`,
            status = item.status
        )
    }
}