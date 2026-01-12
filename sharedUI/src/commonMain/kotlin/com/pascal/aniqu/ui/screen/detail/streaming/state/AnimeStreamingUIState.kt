package com.pascal.aniqu.ui.screen.detail.streaming.state

import com.pascal.aniqu.domain.model.anime.Stream

data class AnimeStreamingUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val stream: Stream? = null
)