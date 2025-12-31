package com.pascal.aniqu.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Dashboard(
	val code: Int,
	val success: Boolean,
	val message: String
)