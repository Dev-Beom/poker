package com.play.poker.game.badugi.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.play.poker.value.Game
import com.play.poker.value.GameStatus
import org.springframework.data.annotation.Id
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

class BadugiGame(
    @Id
    val id: String,
    val cardStore: CardStore,
    val users: MutableList<BadugiUser>,
    val hostUserId: Long,
    val startUserId: Long,
    val status: GameStatus,
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime,
) : Game(id, hostUserId, startUserId, createdAt), Serializable {
    companion object {
        fun of(uuid: UUID, hostUserId: Long) = BadugiGame(
            id = uuid.toString(),
            cardStore = CardStore(),
            users = mutableListOf(BadugiUser.of(hostUserId)),
            hostUserId = hostUserId,
            startUserId = hostUserId,
            status = GameStatus.READY,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        fun of(game: BadugiGame, newUserId: Long) = BadugiGame(
            id = game.id,
            cardStore = game.cardStore,
            users = (game.users + mutableListOf(BadugiUser.of(newUserId))).toMutableList(),
            hostUserId = game.hostUserId,
            startUserId = game.startUserId,
            status = game.status,
            createdAt = game.createdAt,
            updatedAt = LocalDateTime.now()
        )
    }
}