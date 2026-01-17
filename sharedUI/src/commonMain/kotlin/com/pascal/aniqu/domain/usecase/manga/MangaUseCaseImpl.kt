package com.pascal.aniqu.domain.usecase.manga

import com.pascal.aniqu.data.repository.manga.MangaRepository
import com.pascal.aniqu.domain.mapper.manga.toDomain
import com.pascal.aniqu.domain.model.manga.Manga
import com.pascal.aniqu.domain.model.manga.MangaDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class MangaUseCaseImpl(
    private val repository: MangaRepository
) : MangaUseCase {
    override suspend fun getMangaHome(): Flow<Manga> = flow {
        emit(repository.getMangaHome().toDomain())
    }

    override suspend fun getMangaDetail(slug: String): Flow<MangaDetail> = flow {
        emit(repository.getMangaDetail(slug).toDomain())
    }

}
