package com.pascal.aniqu.utils.base

import androidx.navigation.NavController
import kotlinx.serialization.json.Json

inline fun <reified T> saveToCurrentBackStack(
    navController: NavController,
    key: String,
    data: T
) {
    val handle = navController.currentBackStackEntry
        ?.savedStateHandle
        ?: return

    when (data) {
        is String,
        is Int,
        is Double,
        is Float,
        is Long,
        is Boolean -> {
            handle[key] = data
        }
        else -> {
            handle[key] = Json.encodeToString(data)
        }
    }
}

inline fun <reified T> getFromPreviousBackStack(
    navController: NavController,
    key: String
): T? {
    val handle = navController.previousBackStackEntry
        ?.savedStateHandle
        ?: return null

    handle.get<T>(key)?.let { return it }

    val json = handle.get<String>(key) ?: return null
    return runCatching {
        Json.decodeFromString<T>(json)
    }.getOrNull()
}

