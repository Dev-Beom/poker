package com.play.poker.common

import com.play.poker.common.exception.CommonException

class ErrorResponse(val code: Int, val message: String?) {
    companion object {
        fun of(commonException: CommonException) = ErrorResponse(commonException.status.value(), commonException.message)
    }
}