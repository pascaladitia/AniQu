package com.pascal.aniqu.domain.usecase.remote

import androidx.paging.PagingData
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.model.AnimeItem
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    suspend fun getAnimeHome(): Flow<Anime>
    suspend fun getAnimeLive(): Flow<PagingData<AnimeItem>>
}
