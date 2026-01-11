package com.pascal.aniqu.utils

expect fun showToast(msg: String)

expect fun actionShareUrl(url: String?)

fun String.removeEpisodeSuffix(): String {
    return this.replace(Regex("-episode-\\d+$"), "")
}

fun String.cleanAnimeTitle(): String {
    return this
        .replace(
            Regex(
                "\\s*episode\\s*\\d+.*$",
                RegexOption.IGNORE_CASE
            ),
            ""
        )
        .trim()
}

fun String.toEnglishDate(): String {
    val parts = this.split("-")
    if (parts.size != 3) return this

    val year = parts[0]
    val month = parts[1].toIntOrNull() ?: return this
    val day = parts[2]

    val monthName = month.toEnglishMonthName()

    return "$day $monthName $year"
}

fun Int.toEnglishMonthName(): String {
    return when (this) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> ""
    }
}
