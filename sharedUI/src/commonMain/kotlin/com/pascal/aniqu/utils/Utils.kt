package com.pascal.aniqu.utils

expect fun showToast(msg: String)

expect fun actionShareUrl(url: String?)

fun addRandomParam(url: String?): String? {
    if (url == null) return null

    return when {
        url.contains("random=") -> "$url${(0..9999).random()}"
        url.contains("?random") -> "$url${(0..9999).random()}"
        else -> url
    }
}