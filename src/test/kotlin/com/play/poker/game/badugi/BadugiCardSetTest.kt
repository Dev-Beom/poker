package com.play.poker.game.badugi

import com.play.poker.util.dsl.at
import com.play.poker.value.Suit.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class BadugiCardSetTest {
    private val badugiCore = BadugiCore()

    @Test
    fun `getGenealogy - GOLF`() {
        assertDoesNotThrow(
            {
                val cardSet = BadugiCardSet.from(
                    cards = listOf(CLUB at 1, HEART at 2, DIAMOND at 3, SPADE at 4)
                )
                assertThat(cardSet.genealogy).isSameAs(BadugiGenealogy.GOLF)
            },
            "골프는 카드 4장의 무늬와 숫자가 모든 가른 경우인 메이드 중에서 가장 높은 경우이다."
        )
    }

    @Test
    fun `getGenealogy - SECOND`() {
        assertDoesNotThrow(
            {
                val cardSet = BadugiCardSet.from(
                    cards = listOf(CLUB at 1, HEART at 2, DIAMOND at 3, SPADE at 5)
                )
                assertThat(cardSet.genealogy).isSameAs(BadugiGenealogy.SECOND)
            },
            "세컨드는 카드 4장의 무늬와 숫자가 모든 가른 경우인 메이드 중에서 두번째로 높은 경우이다."
        )
    }

    @Test
    fun `getGenealogy - THIRD`() {
        assertDoesNotThrow(
            {
                val cardSet = BadugiCardSet.from(
                    cards = listOf(CLUB at 1, HEART at 2, DIAMOND at 4, SPADE at 5)
                )
                assertThat(cardSet.genealogy).isSameAs(BadugiGenealogy.THIRD)
            },
            "써드는 카드 4장의 무늬와 숫자가 모든 가른 경우인 메이드 중에서 세번째로 높은 경우이다."
        )
    }

    @Test
    fun `getGenealogy - MADE`() {
        assertDoesNotThrow(
            {
                val cardSet = BadugiCardSet.from(
                    cards = listOf(SPADE at 1, CLUB at 4, DIAMOND at 5, HEART at 6)
                )
                assertThat(cardSet.genealogy).isSameAs(BadugiGenealogy.MADE)
            },
            "메이드는 카드 4장의 무늬와 숫자가 모두 다른 경우이다."
        )
    }

    @Test
    fun `getGenealogy - BASE`() {
        assertDoesNotThrow(
            {
                val cardSet = BadugiCardSet.from(
                    cards = listOf(SPADE at 1, CLUB at 4, DIAMOND at 5, DIAMOND at 6)
                )
                assertThat(cardSet.genealogy).isSameAs(BadugiGenealogy.BASE)
            },
            "베이스는 메이드가 아닌 패 중에서 1장을 제외하면 나머지 3장의 무늬와 숫자가 모두 다른 경우이다."
        )
    }

    @Test
    fun `getGenealogy - TWO_BASE`() {
        assertDoesNotThrow(
            {
                val cardSet = BadugiCardSet.from(
                    cards = listOf(SPADE at 1, CLUB at 1, DIAMOND at 5, DIAMOND at 6)
                )
                assertThat(cardSet.genealogy).isSameAs(BadugiGenealogy.TWO_BASE)
            },
            "투베이스는 메이드가 아닌 패 중에서 2장을 제외하면 나머지 2장의 무늬와 숫자가 모두 다른 경우이다."
        )
    }

    @Test
    fun `getGenealogy - NONE`() {
        assertDoesNotThrow(
            {
                val cardSet1 = BadugiCardSet.from(
                    cards = listOf(SPADE at 1, SPADE at 2, SPADE at 3, SPADE at 4)
                )
                val cardSet2 = BadugiCardSet.from(
                    cards = listOf(SPADE at 1, CLUB at 1, DIAMOND at 1, HEART at 1)
                )
                val result1 = cardSet1.genealogy
                val result2 = cardSet2.genealogy
                assertThat(result1).isSameAs(BadugiGenealogy.NONE_TOP)
                assertThat(result2).isSameAs(BadugiGenealogy.NONE_TOP)
            },
            "무탑은 4장의 카드가 모두 같은 숫자이거나 모두 같은 무늬인 경우이다."
        )
    }
}