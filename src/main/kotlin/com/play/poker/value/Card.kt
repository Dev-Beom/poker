package com.play.poker.value

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable


@JsonIgnoreProperties(ignoreUnknown = true)
data class Card(
    val number: Int,
    val suit: Suit
): Serializable {
    val display: String
        get() {
            return when (number) {
                1 -> "A"
                11 -> "J"
                12 -> "Q"
                13 -> "K"
                else -> number.toString()
            }
        }

    override fun toString(): String {
        return "${this.suit} ${this.number}"
    }

    companion object {
        fun sets(suit: Suit) = (1..13).map { Card(number = it, suit) }
    }
}
