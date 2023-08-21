package com.play.poker.game.badugi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BadugiController(
    val badugiService: BadugiService
) {
    @GetMapping("")
    fun test() {
    }
}