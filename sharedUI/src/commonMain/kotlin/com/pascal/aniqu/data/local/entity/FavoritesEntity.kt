package com.pascal.aniqu.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "favorite",
    indices = [Index(value = ["slug"], unique = true)]
)
data class FavoritesEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String? = null,
    val slug: String? = null,
    val poster: String? = null,
    val episodes: String? = null,
    val type: String? = null,
    val status: String? = null,
    val date: String? = null
)
