package com.pascal.aniqu.domain.usecase.anime

import androidx.paging.PagingData
import com.pascal.aniqu.domain.model.anime.AnimeDetail
import com.pascal.aniqu.domain.model.anime.AnimeGenre
import com.pascal.aniqu.domain.model.anime.AnimeItem
import com.pascal.aniqu.domain.model.anime.AnimeStreaming
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    suspend fun getAnimeHome(): Flow<List<AnimeItem>>
    suspend fun getAnimeLive(): Flow<PagingData<AnimeItem>>
    suspend fun getAnimeDetail(slug: String): Flow<AnimeDetail?>
    suspend fun getAnimeGenre(): Flow<List<AnimeGenre>>
    suspend fun getAnimeGenre(slug: String): Flow<List<AnimeItem>>
    suspend fun getAnimeSearch(key: String): Flow<List<AnimeItem>>
    suspend fun getAnimeStreaming(id: String): Flow<AnimeStreaming?>
}
