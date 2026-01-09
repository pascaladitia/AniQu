package com.pascal.aniqu.domain.usecase.anime

import androidx.paging.PagingData
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.model.AnimeDetail
import com.pascal.aniqu.domain.model.AnimeEpisodeDetail
import com.pascal.aniqu.domain.model.AnimeStreaming
import com.pascal.aniqu.domain.model.item.AnimeItem
import com.pascal.aniqu.domain.model.item.Genre
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    suspend fun getAnimeHome(): Flow<Anime>
    suspend fun getAnimeLive(): Flow<PagingData<AnimeItem>>
    suspend fun getAnimeDetail(slug: String): Flow<AnimeDetail>
    suspend fun getAnimeGenre(): Flow<List<Genre>>
    suspend fun getAnimeGenre(slug: String): Flow<List<AnimeItem>>
    suspend fun getAnimeSearch(key: String): Flow<List<AnimeItem>>
    suspend fun getAnimeEpisode(slug: String): Flow<AnimeEpisodeDetail>
    suspend fun getAnimeStreaming(id: String): Flow<AnimeStreaming>
}
