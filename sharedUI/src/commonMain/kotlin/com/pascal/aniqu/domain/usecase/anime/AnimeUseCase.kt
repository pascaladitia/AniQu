package com.pascal.aniqu.domain.usecase.anime

import androidx.paging.PagingData
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.model.AnimeDetail
import com.pascal.aniqu.domain.model.AnimeItem
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    suspend fun getAnimeHome(): Flow<Anime>
    suspend fun getAnimeLive(): Flow<PagingData<AnimeItem>>
    suspend fun getAnimeDetail(slug: String): Flow<AnimeDetail>
}
