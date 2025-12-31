package com.pascal.aniqu.ui.screen.watchlist.state

import com.pascal.aniqu.domain.model.StockRecommendation

data class WatchListUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val stockRecommendation: List<StockRecommendation>? = null
)