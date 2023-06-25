package com.play.poker.game.badugi

import com.play.poker.util.Combination
import com.play.poker.value.Card

class BadugiCardSet(
    private val cards: List<Card>,
) {
    val genealogy: BadugiGenealogy by lazy {
        when {
            isGolf() -> BadugiGenealogy.GOLF
            isSecond() -> BadugiGenealogy.SECOND
            isThird() -> BadugiGenealogy.THIRD
            isMade() -> BadugiGenealogy.MADE
            isBase() -> BadugiGenealogy.BASE
            isTwoBase() -> BadugiGenealogy.TWO_BASE
            isNoneTop() -> BadugiGenealogy.NONE_TOP
            else -> throw Exception("badugi card set get genealogy exception.")
        }
    }

    override fun toString(): String {
        check(cards.size == FIXED_CARD_SIZE) { "badugi card set size exception." }
        return cards.joinToString(separator = "") { it.display }
    }

    private fun BadugiCardSet.isGolf(): Boolean {
        return isMade() && matchOf("A234")
    }

    private fun BadugiCardSet.isSecond(): Boolean {
        println(this.cards.toString())
        return isMade() && matchOf("A235")
    }

    private fun BadugiCardSet.isThird(): Boolean {
        return isMade() && matchOf("A245")
    }

    private fun BadugiCardSet.isMade(): Boolean {
        return cards.isAllDiffNumber() && cards.isAllDiffSuit()
    }

    private fun BadugiCardSet.isBase(): Boolean {
        return !isMade() && cards.isDiffNumberAndSuitFor(3)
    }

    private fun BadugiCardSet.isTwoBase(): Boolean {
        return !isMade() && cards.isDiffNumberAndSuitFor(2)
    }

    private fun BadugiCardSet.isNoneTop(): Boolean {
        return cards.isAllSameNumber() || cards.isAllSameSuit()
    }

    private fun List<Card>.isDiffNumberAndSuitFor(countOfCard: Int): Boolean {
        val combinations = Combination.combinations(this, countOfCard)
        return combinations.any { it.isAllDiffNumber() && it.isAllDiffSuit() }
    }

    private fun List<Card>.isAllDiffNumber(): Boolean {
        return none { card -> count { it.number == card.number } != 1 }
    }

    private fun List<Card>.isAllDiffSuit(): Boolean {
        return none { card -> count { it.suit == card.suit } != 1 }
    }

    private fun List<Card>.isAllSameNumber(): Boolean {
        return count { it.number == first().number } == size
    }

    private fun List<Card>.isAllSameSuit(): Boolean {
        return count { it.suit == first().suit } == size
    }

    private fun BadugiCardSet.matchOf(string: String): Boolean {
        return this.toString() == string
    }

    companion object {
        private const val FIXED_CARD_SIZE = 4

        fun from(cards: List<Card>): BadugiCardSet {
            check(cards.size == FIXED_CARD_SIZE) { "badugi card set size exception." }
            val sortedCards = cards.sortedBy { it.number }
            return BadugiCardSet(sortedCards)
        }
    }
}