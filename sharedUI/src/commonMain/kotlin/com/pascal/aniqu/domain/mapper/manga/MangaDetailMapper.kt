package com.pascal.aniqu.domain.mapper.manga

import com.pascal.aniqu.data.remote.dtos.manga.MangaDetailResponse
import com.pascal.aniqu.domain.model.manga.MangaDetail
import com.pascal.aniqu.domain.model.manga.MangaDetailData
import com.pascal.aniqu.domain.model.manga.MangaInfo

fun MangaDetailResponse.toDomain(): MangaDetail =
    MangaDetail(
        creator = creator.orEmpty(),
        success = success ?: false,
        details = MangaDetailData(
            title = details?.title.orEmpty(),
            alternative = details?.alternative.orEmpty(),
            image = details?.image.orEmpty(),
            rating = details?.rating.orEmpty(),
            synopsis = details?.synopsis.orEmpty(),
            info = MangaInfo(
                status = details?.info?.status.orEmpty(),
                type = details?.info?.type.orEmpty(),
                released = details?.info?.released.orEmpty(),
                author = details?.info?.author.orEmpty(),
                artist = details?.info?.artist.orEmpty(),
                serialization = details?.info?.serialization.orEmpty(),
                postedBy = details?.info?.posted_by.orEmpty(),
                postedOn = details?.info?.posted_on.orEmpty(),
                updatedOn = details?.info?.updated_on.orEmpty(),
                views = details?.info?.views.orEmpty()
            ),
            genres = details?.genres.orEmpty().filterNotNull(),
            chapters = details?.chapters.orEmpty()
                .filterNotNull()
                .map { it.toDomain() }
        )
    )