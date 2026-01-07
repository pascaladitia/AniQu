package com.pascal.aniqu.ui.screen.manga.state

import com.pascal.aniqu.domain.model.StockRecommendation

data class MangaUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val stockRecommendation: List<StockRecommendation>? = null
)