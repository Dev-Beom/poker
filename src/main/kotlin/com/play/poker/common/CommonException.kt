package com.play.poker.common

import kotlin.Exception

class CommonException(override val message: String = "common exception") : Exception(message) {
    companion object {
        val GAME_NOT_FOUND = CommonException("Can't find the game.");
        val GAME_USERS_FULL = CommonException("The number of gamers is full.")
        val GAME_IS_IN_GAME = CommonException("The game is in play.")
        val USER_NOT_FOUND_IN_GAME = CommonException("This is non-existent user id in game.")
        val USER_CANT_OUT_GAME = CommonException("The user cannot leave the game.")
    }
}