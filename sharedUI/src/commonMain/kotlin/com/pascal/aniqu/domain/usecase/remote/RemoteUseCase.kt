package com.pascal.aniqu.domain.usecase.remote

import androidx.paging.PagingData
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.model.AnimeHome
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    suspend fun getAnimeHome(): Flow<AnimeHome>
    suspend fun getAnimeList(): Flow<PagingData<Anime>>
}
