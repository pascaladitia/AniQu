package com.pascal.aniqu.data.preferences

import com.pascal.aniqu.createSettings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

object PrefLogin {
    private const val IS_LOGIN = "is_login"
    private const val RESPONSE_LOGIN = "response_login"

    fun setIsLogin(value: Boolean) {
        createSettings()[IS_LOGIN] = value
    }

    fun getIsLogin(): Boolean {
        return createSettings()[IS_LOGIN, false]
    }

    fun clear() {
        createSettings().clear()
    }
}