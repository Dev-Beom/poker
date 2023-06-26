package com.play.poker.game.badugi

import com.play.poker.util.dsl.at
import com.play.poker.value.Suit.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class BadugiCardSetTest {
    @Test
    fun `genealogy - ALL`() {
        assertDoesNotThrow(
            {
                val cardSets = listOf(
                    BadugiCardSet.from(listOf(SPADE at 1, CLUB at 4, DIAMOND at 5, DIAMOND at 6)),
                    BadugiCardSet.from(listOf(SPADE at 1, CLUB at 1, DIAMOND at 5, DIAMOND at 6)),
                    BadugiCardSet.from(listOf(CLUB at 1, HEART at 2, DIAMOND at 3, SPADE at 4)),
                    BadugiCardSet.from(listOf(CLUB at 1, HEART at 2, DIAMOND at 3, SPADE at 5)),
                    BadugiCardSet.from(listOf(CLUB at 1, HEART at 2, DIAMOND at 4, SPADE at 5)),
                    BadugiCardSet.from(listOf(SPADE at 1, SPADE at 2, SPADE at 3, SPADE at 4)),
                    BadugiCardSet.from(listOf(SPADE at 1, CLUB at 1, DIAMOND at 1, HEART at 1)),
                    BadugiCardSet.from(listOf(SPADE at 1, CLUB at 4, DIAMOND at 5, HEART at 6))
                )
                val result = cardSets.sortedDescending()
                assertThat(result[0].genealogy).isSameAs(BadugiGenealogy.GOLF)
                assertThat(result[1].genealogy).isSameAs(BadugiGenealogy.SECOND)
                assertThat(result[2].genealogy).isSameAs(BadugiGenealogy.THIRD)
                assertThat(result[3].genealogy).isSameAs(BadugiGenealogy.MADE)
                assertThat(result[4].genealogy).isSameAs(BadugiGenealogy.BASE)
                assertThat(result[5].genealogy).isSameAs(BadugiGenealogy.TWO_BASE)
                assertThat(result[6].genealogy).isSameAs(BadugiGenealogy.NONE_TOP)
                assertThat(result[7].genealogy).isSameAs(BadugiGenealogy.NONE_TOP)
            },
            "바둑이 족보"
        )
    }

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
        assertDoesNotThrow(
            {
                val cardSet1 = BadugiCardSet.from(
                    cards = listOf(SPADE at 1, CLUB at 2, DIAMOND at 3, DIAMOND at 4)
                )
                val cardSet2 = BadugiCardSet.from(
                    cards = listOf(SPADE at 1, CLUB at 2, DIAMOND at 3, HEART at 3)
                )
                val cardSet3 = BadugiCardSet.from(
                    cards = listOf(CLUB at 5, SPADE at 7, HEART at 13, HEART at 10)
                )
                val cardSet4 = BadugiCardSet.from(
                    cards = listOf(SPADE at 9, DIAMOND at 10, HEART at 2, DIAMOND at 5)
                )
                val cardSet5 = BadugiCardSet.from(
                    cards = listOf(HEART at 1, SPADE at 5, HEART at 7, DIAMOND at 8)
                )
                listOf(cardSet1, cardSet2, cardSet3, cardSet4, cardSet5).sortedDescending()
                    .forEach { println(it) }
            },
            "무늬가 같은 경우엔 조합 중에서 가장 높은 패가 제외된다." +
                    "숫자가 같은 경우엔 조합 중에서 가장 중복이 없는 패가 제외된다."
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