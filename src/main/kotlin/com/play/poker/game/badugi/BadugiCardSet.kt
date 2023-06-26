package com.play.poker.game.badugi

import com.play.poker.game.badugi.BadugiGenealogy.*
import com.play.poker.util.Combination
import com.play.poker.value.Card

class BadugiCardSet(
    private val cards: List<Card>,
) : Comparable<BadugiCardSet> {
    private val descOrderedCards: List<Card> = cards.sortedByDescending { it.number }

    val genealogy: BadugiGenealogy = when {
        isGolf() -> GOLF
        isSecond() -> SECOND
        isThird() -> THIRD
        isMade() -> MADE
        isBase() -> BASE
        isTwoBase() -> TWO_BASE
        isNoneTop() -> NONE_TOP
        else -> throw Exception("badugi card set get genealogy exception.")
    }

    private val display: String? = when (genealogy) {
        MADE, BASE, TWO_BASE, NONE_TOP -> getCombinations(genealogy.effectiveCount).sortedBy { it.number }.toString()
        else -> null
    }

    override fun compareTo(other: BadugiCardSet): Int {
        return when {
            genealogy.priority < other.genealogy.priority -> 1
            genealogy.priority > other.genealogy.priority -> -1
            else -> samePriorityCompareTo(other)
        }
    }

    override fun toString(): String {
        return cards.joinToString(separator = ", ") { "${it.suit} ${it.display}" } + " | $genealogy ${display ?: ""}"
    }

    private fun BadugiCardSet.samePriorityCompareTo(other: BadugiCardSet): Int {
        val currentBinary = getCombinations(this.genealogy.effectiveCount).toBinary()
        val otherBinary = other.getCombinations(this.genealogy.effectiveCount).toBinary()
        return when {
            currentBinary < otherBinary -> 1
            currentBinary > otherBinary -> -1
            else -> 0
        }
    }

    private fun toBinaryString(): String {
        check(cards.size == FIXED_CARD_SIZE) { "badugi card set size exception." }
        return cards.joinToString(separator = "") { it.display }
    }

    private fun BadugiCardSet.isGolf(): Boolean {
        return isMade() && matchOf("A234")
    }

    private fun BadugiCardSet.isSecond(): Boolean {
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

    private fun getCombinations(countOfCard: Int): List<Card> {
        return Combination.combinations(cards, countOfCard)
            .filter { it.isAllDiffNumber() && it.isAllDiffSuit() }
            .map { combination -> combination.sortedByDescending { it.number } }
            .findMinCombination()
    }

    private fun List<List<Card>>.findMinCombination(): List<Card> {
        return this.minByOrNull { it.toBinary() } ?: emptyList()
    }

    private fun List<Card>.toBinary(): Int {
        return this.map { it.number }.joinToString("").toIntOrNull() ?: 0
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
        return this.toBinaryString() == string
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