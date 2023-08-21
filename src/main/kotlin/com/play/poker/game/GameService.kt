package com.play.poker.game

import java.util.UUID

interface GameService {
    fun create(hostUserId: Long): UUID
    fun join(uuid: UUID)
    fun exit(uuid: UUID, userId: Long)
}