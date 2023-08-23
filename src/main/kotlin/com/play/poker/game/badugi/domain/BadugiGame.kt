package com.play.poker.game.badugi.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.play.poker.value.Game
import org.springframework.data.annotation.Id
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

class BadugiGame(
    @Id
    val id: String,
    val cardStore: CardStore,
    val users: List<BadugiUser>,
    val hostUserId: Long,
    val startUserId: Long,

    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
) : Game(id, hostUserId, startUserId, createdAt), Serializable {
    companion object {
        fun of(uuid: UUID, hostUserId: Long) = BadugiGame(
            id = uuid.toString(),
            cardStore = CardStore(),
            users = listOf(BadugiUser.of(hostUserId)),
            hostUserId = hostUserId,
            startUserId = hostUserId,
            createdAt = LocalDateTime.now()
        )
    }
}