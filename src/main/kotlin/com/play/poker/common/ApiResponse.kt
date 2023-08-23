package com.play.poker.common

class ApiResponse<T>(
    val code: String = "ok",
    val data: T?
) {
    companion object {
        fun <T> success(data: T?): ApiResponse<T> = ApiResponse(data = data)
    }
}