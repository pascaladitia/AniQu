package com.pascal.aniqu.domain.usecase.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pascal.aniqu.data.remote.api.KtorClientApi
import com.pascal.aniqu.domain.mapper.toDomain
import com.pascal.aniqu.domain.model.Anime

class AnimePagingSource(
    private val api: KtorClientApi
) : PagingSource<Int, Anime>() {

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        return try {
            val page = params.key ?: 1

            val animeList: List<Anime> = api.getAnimeList(page)
                .data.map {
                    it.toDomain()
                }

            LoadResult.Page(
                data = animeList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (animeList.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
