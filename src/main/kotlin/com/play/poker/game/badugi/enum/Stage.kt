package com.play.poker.game.badugi.enum

enum class Stage(
    val title: String,
    val order: Int
) {
    MORNING(title = "아침", order = 0),
    AFTERNOON(title = "점심", order = 1),
    EVENING(title = "저녁", order = 2)
}