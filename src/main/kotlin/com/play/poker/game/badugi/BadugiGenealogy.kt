package com.play.poker.game.badugi

enum class BadugiGenealogy(
    val title: String,
    val priority: Int
) {
    GOLF(
        title = "골프",
        priority = 0
    ),
    SECOND(
        title = "세컨드",
        priority = 1
    ),
    THIRD(
        title = "써드",
        priority = 2
    ),
    MADE(
        title = "메이드",
        priority = 3
    ),
    BASE(
        title = "베이스",
        priority = 4
    ),
    TWO_BASE(
        title = "투베이스",
        priority = 5
    ),
    NONE_TOP(
        title = "무탑",
        priority = 6
    )
}