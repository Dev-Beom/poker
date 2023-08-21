package com.play.poker.game.badugi.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardStoreTest {
    @Test
    fun `source test`() {
        CardStore.SOURCE.forEach { println(it) }
    }
}