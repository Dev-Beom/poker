package com.play.poker.game.badugi

import com.play.poker.common.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/badugi")
class BadugiController(
    val badugiService: BadugiService
) {
    @PostMapping
    fun create(): ResponseEntity<ApiResponse<String>> {
        val userIdFromHeader = 0L
        val uuid = badugiService.create(userIdFromHeader)
        return ResponseEntity.ok(ApiResponse.success(uuid.toString()))
    }
}