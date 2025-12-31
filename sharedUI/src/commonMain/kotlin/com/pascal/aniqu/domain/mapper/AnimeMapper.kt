package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeAttributes
import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeData
import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeResponse
import com.pascal.aniqu.domain.model.*


fun AnimeResponse.toDomain(): List<Anime> =
    data.mapNotNull { it.toDomain() }

fun AnimeData.toDomain(): Anime {
    return Anime(
        id = id.orEmpty(),
        type = type.orEmpty(),
        title = attributes?.toDomainTitle(),
        synopsis = attributes?.synopsis,
        description = attributes?.description,
        images = attributes?.toDomainImages(),
        rating = attributes?.toDomainRating(),
        episode = attributes?.toDomainEpisode(),
        status = attributes?.toDomainStatus(),
        dates = attributes?.toDomainDates(),
        isNsfw = attributes?.nsfw ?: false
    )
}

fun AnimeAttributes.toDomainTitle(): AnimeTitle =
    AnimeTitle(
        canonical = canonicalTitle ?: "-",
        english = titles?.en
            ?: titles?.enJp
            ?: titles?.enUs,
        japanese = titles?.jaJp
    )

fun AnimeAttributes.toDomainImages(): AnimeImages =
    AnimeImages(
        poster = posterImage?.medium
            ?: posterImage?.large
            ?: posterImage?.original,
        cover = coverImage?.large
            ?: coverImage?.original
    )

fun AnimeAttributes.toDomainRating(): AnimeRating =
    AnimeRating(
        average = averageRating?.toDoubleOrNull(),
        rank = ratingRank,
        popularityRank = popularityRank
    )

fun AnimeAttributes.toDomainEpisode(): AnimeEpisode =
    AnimeEpisode(
        count = episodeCount,
        length = episodeLength,
        totalLength = totalLength
    )

fun AnimeAttributes.toDomainStatus(): AnimeStatus =
    AnimeStatus(
        status = status ?: "unknown",
        subtype = subtype ?: "unknown",
        ageRating = ageRating
    )

fun AnimeAttributes.toDomainDates(): AnimeDates =
    AnimeDates(
        startDate = startDate,
        endDate = endDate
    )
