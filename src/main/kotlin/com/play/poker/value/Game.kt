package com.play.poker.value

import java.time.LocalDateTime

open class Game(
    private val id: String,
    private val hostUserId: Long,
    private val startUserId: Long,
    private val createdAt: LocalDateTime
)
