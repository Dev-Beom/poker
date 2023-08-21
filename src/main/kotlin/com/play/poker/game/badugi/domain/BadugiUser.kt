package com.play.poker.game.badugi.domain

import com.play.poker.value.Card
import java.io.Serializable

class BadugiUser(
    private val userId: Long,
    private val cards: List<Card> = emptyList()
): Serializable {
    companion object {
        fun of(userId: Long) = BadugiUser(
            userId = userId,
            cards = emptyList()
        )

        fun of(userId: Long, cards: List<Card>) = BadugiUser(
            userId = userId,
            cards = cards
        )
    }
}