package com.play.poker.game.badugi

import com.play.poker.value.Card

class BadugiCardSet(
    private val cards: List<Card>
) {
    companion object {
        private const val FIXED_CARD_SIZE = 4

        fun from(cards: List<Card>): BadugiCardSet {
            check(cards.size == FIXED_CARD_SIZE) { "badugi card set size exception." }
            return BadugiCardSet(cards = cards)
        }
    }

    fun orderedCards(): List<Card> {
        check(cards.size == FIXED_CARD_SIZE) { "badugi card set size exception." }
        return cards.sortedBy { it.number }
    }

    override fun toString(): String {
        check(cards.size == FIXED_CARD_SIZE) { "badugi card set size exception." }
        return cards.joinToString(separator = "") { it.display }
    }
}