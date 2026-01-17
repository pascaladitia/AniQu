package com.pascal.aniqu.utils

enum class ScreenOrientation {
    LANDSCAPE,
    PORTRAIT
}

expect fun setScreenOrientation(orientation: ScreenOrientation)
