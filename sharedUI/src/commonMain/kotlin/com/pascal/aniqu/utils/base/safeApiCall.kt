package com.pascal.aniqu.utils.base

import com.pascal.aniqu.data.remote.dtos.BaseResponse

suspend inline fun <T, R> safeApiCall(
    crossinline apiCall: suspend () -> BaseResponse<T>,
    crossinline mapper: T.() -> R
): R {
    val response = apiCall()

    if (response.statusCode == 200 && response.data != null) {
        return response.data.mapper()
    } else {
        throw ApiException(
            code = response.statusCode ?: -1,
            message = response.message ?: response.statusMessage ?: "Unknown error"
        )
    }
}

class ApiException(
    val code: Int,
    override val message: String
) : RuntimeException(message)

