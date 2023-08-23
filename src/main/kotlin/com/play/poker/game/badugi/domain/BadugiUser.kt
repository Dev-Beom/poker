package com.play.poker.game.badugi.domain

import com.play.poker.value.Betting
import com.play.poker.value.Card
import com.play.poker.value.UserStatus
import java.io.Serializable

class BadugiUser(
    val userId: Long,
    val cards: List<Card> = emptyList(),
    val status: UserStatus,
    val bet: Betting,
) : Serializable {
    companion object {
        fun of(userId: Long) = BadugiUser(
            userId = userId,
            cards = emptyList(),
            status = UserStatus.READY,
            bet = Betting.NONE
        )

        fun of(userId: Long, cards: List<Card>) = BadugiUser(
            userId = userId,
            cards = cards,
            status = UserStatus.READY,
            bet = Betting.NONE
        )
    }
}