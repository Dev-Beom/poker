package com.play.poker.value

import java.awt.Color

enum class Suit(
    val symbol: String,
    val displayName: String,
    val color: Color
) {
    SPADE(
        "♠",
        displayName = "스페이드",
        color = Color.black
    ),
    HEART(
        symbol = "♥",
        displayName = "하트",
        color = Color.red
    ),
    DIAMOND(
        symbol = "♦",
        displayName = "다이아몬드",
        color = Color.red
    ),
    CLUB(
        symbol = "♣",
        displayName = "클로바",
        color = Color.black
    )
}