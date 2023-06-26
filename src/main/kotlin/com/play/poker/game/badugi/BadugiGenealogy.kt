package com.play.poker.game.badugi

enum class BadugiGenealogy(
    val title: String,
    val priority: Int,
    val effectiveCount: Int,
) {
    GOLF(
        title = "골프",
        priority = 0,
        effectiveCount = 4
    ),
    SECOND(
        title = "세컨드",
        priority = 1,
        effectiveCount = 4
    ),
    THIRD(
        title = "써드",
        priority = 2,
        effectiveCount = 4
    ),
    MADE(
        title = "메이드",
        priority = 3,
        effectiveCount = 4
    ),
    BASE(
        title = "베이스",
        priority = 4,
        effectiveCount = 3
    ),
    TWO_BASE(
        title = "투베이스",
        priority = 5,
        effectiveCount = 2
    ),
    NONE_TOP(
        title = "무탑",
        priority = 6,
        effectiveCount = 1
    )
}