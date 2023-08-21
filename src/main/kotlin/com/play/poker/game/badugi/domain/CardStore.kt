package com.play.poker.game.badugi.domain

import com.play.poker.value.Card
import com.play.poker.value.Suit.*
import java.io.Serializable

class CardStore(
    val refreshCards: List<Card> = emptyList(),
): Serializable {
    val cards: List<Card> = SOURCE

    companion object {
        val SOURCE = Card.sets(SPADE) + Card.sets(HEART) + Card.sets(CLUB) + Card.sets(DIAMOND)
    }

//    private fun exchange(card: Card): CardStore {
//
//    }
}