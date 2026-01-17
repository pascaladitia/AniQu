package com.pascal.aniqu.utils

import android.content.pm.ActivityInfo
import com.pascal.aniqu.ContextUtils

actual fun setScreenOrientation(orientation: ScreenOrientation) {
    ContextUtils.activity.requestedOrientation =
        when (orientation) {
            ScreenOrientation.LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            ScreenOrientation.PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
}
