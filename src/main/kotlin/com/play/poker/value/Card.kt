package com.play.poker.value

data class Card(
    val number: Int,
    val suit: Suit
) {
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
}
