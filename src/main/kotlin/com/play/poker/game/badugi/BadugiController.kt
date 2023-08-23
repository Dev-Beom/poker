package com.play.poker.game.badugi

import com.play.poker.game.badugi.domain.BadugiGame
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BadugiController(
    val badugiService: BadugiService
) {
    @PostMapping
    fun create(): ResponseEntity<String> {

    }
}