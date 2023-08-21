package com.play.poker.game.badugi.domain

import com.play.poker.value.Game
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable
import java.util.UUID

@RedisHash("badugi-game")
class BadugiGame(
    @Id
    val id: String,
    val cardStore: CardStore,
    val users: List<BadugiUser>,
    val hostUserId: Long,
    val startUserId: Long
) : Game(id, hostUserId, startUserId), Serializable {
    companion object {
        fun of(uuid: UUID, hostUserId: Long) = BadugiGame(
            id = uuid.toString(),
            cardStore = CardStore(),
            users = listOf(BadugiUser.of(hostUserId)),
            hostUserId = hostUserId,
            startUserId = hostUserId
        )
    }
}