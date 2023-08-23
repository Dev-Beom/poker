package com.play.poker.common.exception

import org.springframework.http.HttpStatus
import kotlin.Exception

class CommonException(override val message: String = "common exception", val status: HttpStatus) : Exception(message) {
    companion object {
        val GAME_NOT_FOUND = CommonException("Can't find the game.", HttpStatus.NOT_FOUND);
        val GAME_USERS_FULL = CommonException("The number of gamers is full.", HttpStatus.BAD_REQUEST)
        val GAME_IS_IN_GAME = CommonException("The game is in play.", HttpStatus.BAD_REQUEST)
        val USER_NOT_FOUND_IN_GAME = CommonException("This is non-existent user id in game.", HttpStatus.NOT_FOUND)
        val USER_CANT_OUT_GAME = CommonException("The user cannot leave the game.", HttpStatus.BAD_REQUEST)
    }
}