package com.play.poker.game.badugi

import com.play.poker.util.Combination
import com.play.poker.value.Card

class BadugiCardSet(
    private val cards: List<Card>,
) {
    val genealogy: BadugiGenealogy by lazy {
        when {
            this.isGolf() -> BadugiGenealogy.GOLF
            this.isSecond() -> BadugiGenealogy.SECOND
            this.isThird() -> BadugiGenealogy.THIRD
            this.isMade() -> BadugiGenealogy.MADE
            this.isBase() -> BadugiGenealogy.BASE
            this.isTwoBase() -> BadugiGenealogy.TWO_BASE
            this.isNoneTop() -> BadugiGenealogy.NONE_TOP
            else -> throw Exception("badugi card set get genealogy exception.")
        }
    }

    override fun toString(): String {
        check(cards.size == FIXED_CARD_SIZE) { "badugi card set size exception." }
        return cards.joinToString(separator = "") { it.display }
    }

    private fun BadugiCardSet.isGolf(): Boolean {
        return this.isMade() && this.matchOf("A234")
    }

    private fun BadugiCardSet.isSecond(): Boolean {
        println(this.cards.toString())
        return this.isMade() && this.matchOf("A235")
    }

    private fun BadugiCardSet.isThird(): Boolean {
        return this.isMade() && this.matchOf("A245")
    }

    private fun BadugiCardSet.isMade(): Boolean {
        return this.cards.isAllDiffNumber() && this.cards.isAllDiffSuit()
    }

    private fun BadugiCardSet.isBase(): Boolean {
        return !this.isMade() && this.cards.isDiffNumberAndSuitFor(3)
    }

    private fun BadugiCardSet.isTwoBase(): Boolean {
        return !this.isMade() && this.cards.isDiffNumberAndSuitFor(2)
    }

    private fun BadugiCardSet.isNoneTop(): Boolean {
        return this.cards.isAllSameNumber() || this.cards.isAllSameSuit()
    }

    private fun List<Card>.isDiffNumberAndSuitFor(countOfCard: Int): Boolean {
        val combinations = Combination.combinations(this, countOfCard)
        for (combination in combinations) {
            if (combination.isAllDiffNumber() && combination.isAllDiffSuit()) return true
        }
        return false
    }

    private fun List<Card>.isAllDiffNumber(): Boolean {
        return this.none { card -> this.filter { it.number == card.number }.size != 1 }
    }

    private fun List<Card>.isAllDiffSuit(): Boolean {
        return this.none { card -> this.filter { it.suit == card.suit }.size != 1 }
    }

    private fun List<Card>.isAllSameNumber(): Boolean {
        return this.count { current -> current.number == this.first().number } == this.count()
    }

    private fun List<Card>.isAllSameSuit(): Boolean {
        return this.count { current -> current.suit == this.first().suit } == this.count()
    }

    private fun BadugiCardSet.matchOf(string: String): Boolean {
        return this.toString() == string
    }

    companion object {
        private const val FIXED_CARD_SIZE = 4

        fun from(cards: List<Card>): BadugiCardSet {
            check(cards.size == FIXED_CARD_SIZE) { "badugi card set size exception." }
            val sortedCards = cards.sortedBy { it.number }
            return BadugiCardSet(cards = sortedCards)
        }
    }
}