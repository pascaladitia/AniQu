package com.pascal.aniqu.data.remote.dtos.dashboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    @SerialName("data")
    val data: List<AnimeData> = emptyList()
)

@Serializable
data class AnimeData(
    @SerialName("id")
    val id: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("links")
    val links: SelfLink? = null,

    @SerialName("attributes")
    val attributes: AnimeAttributes? = null,

    @SerialName("relationships")
    val relationships: Map<String, Relationship> = emptyMap()
)

@Serializable
data class SelfLink(
    @SerialName("self")
    val self: String? = null
)

@Serializable
data class Relationship(
    @SerialName("links")
    val links: RelationshipLinks? = null
)

@Serializable
data class RelationshipLinks(
    @SerialName("self")
    val self: String? = null,

    @SerialName("related")
    val related: String? = null
)

@Serializable
data class AnimeAttributes(

    @SerialName("createdAt")
    val createdAt: String? = null,

    @SerialName("updatedAt")
    val updatedAt: String? = null,

    @SerialName("slug")
    val slug: String? = null,

    @SerialName("synopsis")
    val synopsis: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("coverImageTopOffset")
    val coverImageTopOffset: Int? = null,

    @SerialName("titles")
    val titles: Titles? = null,

    @SerialName("canonicalTitle")
    val canonicalTitle: String? = null,

    @SerialName("abbreviatedTitles")
    val abbreviatedTitles: List<String> = emptyList(),

    @SerialName("averageRating")
    val averageRating: String? = null,

    @SerialName("ratingFrequencies")
    val ratingFrequencies: Map<String, String> = emptyMap(),

    @SerialName("userCount")
    val userCount: Int? = null,

    @SerialName("favoritesCount")
    val favoritesCount: Int? = null,

    @SerialName("startDate")
    val startDate: String? = null,

    @SerialName("endDate")
    val endDate: String? = null,

    @SerialName("nextRelease")
    val nextRelease: String? = null,

    @SerialName("popularityRank")
    val popularityRank: Int? = null,

    @SerialName("ratingRank")
    val ratingRank: Int? = null,

    @SerialName("ageRating")
    val ageRating: String? = null,

    @SerialName("ageRatingGuide")
    val ageRatingGuide: String? = null,

    @SerialName("subtype")
    val subtype: String? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("tba")
    val tba: String? = null,

    @SerialName("posterImage")
    val posterImage: ImageSet? = null,

    @SerialName("coverImage")
    val coverImage: ImageSet? = null,

    @SerialName("episodeCount")
    val episodeCount: Int? = null,

    @SerialName("episodeLength")
    val episodeLength: Int? = null,

    @SerialName("totalLength")
    val totalLength: Int? = null,

    @SerialName("youtubeVideoId")
    val youtubeVideoId: String? = null,

    @SerialName("showType")
    val showType: String? = null,

    @SerialName("nsfw")
    val nsfw: Boolean? = null
)

@Serializable
data class Titles(
    @SerialName("en")
    val en: String? = null,

    @SerialName("en_jp")
    val enJp: String? = null,

    @SerialName("en_us")
    val enUs: String? = null,

    @SerialName("ja_jp")
    val jaJp: String? = null
)

@Serializable
data class ImageSet(
    @SerialName("tiny")
    val tiny: String? = null,

    @SerialName("small")
    val small: String? = null,

    @SerialName("medium")
    val medium: String? = null,

    @SerialName("large")
    val large: String? = null,

    @SerialName("original")
    val original: String? = null,

    @SerialName("meta")
    val meta: ImageMeta? = null
)

@Serializable
data class ImageMeta(
    @SerialName("dimensions")
    val dimensions: Map<String, ImageDimension> = emptyMap()
)

@Serializable
data class ImageDimension(
    @SerialName("width")
    val width: Int? = null,

    @SerialName("height")
    val height: Int? = null
)
