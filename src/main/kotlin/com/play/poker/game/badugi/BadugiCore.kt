package com.play.poker.game.badugi

import com.play.poker.util.Combination
import com.play.poker.value.Card

class BadugiCore {

    fun getGenealogy(badugiCardSet: BadugiCardSet): BadugiGenealogy {
        return when {
            badugiCardSet.isGolf() -> BadugiGenealogy.GOLF
            badugiCardSet.isSecond() -> BadugiGenealogy.SECOND
            badugiCardSet.isThird() -> BadugiGenealogy.THIRD
            badugiCardSet.isMade() -> BadugiGenealogy.MADE
            badugiCardSet.isBase() -> BadugiGenealogy.BASE
            badugiCardSet.isTwoBase() -> BadugiGenealogy.TWO_BASE
            badugiCardSet.isNoneTop() -> BadugiGenealogy.NONE_TOP
            else -> throw Exception("badugi card set get genealogy exception.")
        }
    }

    private fun BadugiCardSet.isGolf(): Boolean {
        return this.isMade() && this.matchOf("A234")
    }

    private fun BadugiCardSet.isSecond(): Boolean {
        return this.isMade() && this.matchOf("A235")
    }

    private fun BadugiCardSet.isThird(): Boolean {
        return this.isMade() && this.matchOf("A245")
    }

    private fun BadugiCardSet.isMade(): Boolean {
        return this.isAllDiffNumber() && this.isAllDiffSuit()
    }

    private fun BadugiCardSet.isBase(): Boolean {
        return !this.isMade() && this.isDiffNumberAndSuitFor(3)
    }

    private fun BadugiCardSet.isTwoBase(): Boolean {
        return !this.isMade() && this.isDiffNumberAndSuitFor(2)
    }

    private fun BadugiCardSet.isNoneTop(): Boolean {
        return this.orderedCards().isAllSameNumber() || this.orderedCards().isAllSameSuit()
    }

    private fun BadugiCardSet.isAllDiffNumber(): Boolean {
        return this.orderedCards().isAllDiffNumber()
    }

    private fun BadugiCardSet.isAllDiffSuit(): Boolean {
        return this.orderedCards().isAllDiffSuit()
    }

    private fun BadugiCardSet.isDiffNumberAndSuitFor(countOfCard: Int): Boolean {
        val orderedCards = this.orderedCards()
        val combinations = Combination.combinations(orderedCards, countOfCard)
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
}