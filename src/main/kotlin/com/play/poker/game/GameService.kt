package com.play.poker.game

import com.play.poker.value.Game
import java.util.UUID

interface GameService {
    fun create(hostUserId: Long): UUID
    fun join(uuid: UUID, userId: Long)
    fun exit(uuid: UUID, userId: Long)
    fun get(uuid: UUID): Game
}