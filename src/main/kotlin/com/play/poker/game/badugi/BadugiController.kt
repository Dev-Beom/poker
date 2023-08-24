package com.play.poker.game.badugi

import com.play.poker.common.ApiResponse
import com.play.poker.game.badugi.domain.BadugiGame
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/badugi")
class BadugiController(
    val badugiService: BadugiService,
) {
    @PostMapping
    fun create(): ResponseEntity<ApiResponse<String>> {
        val userIdFromHeader = 0L
        val uuid = badugiService.create(userIdFromHeader)
        return ResponseEntity.ok(ApiResponse.success(uuid.toString()))
    }

    @GetMapping("/{uuid}")
    fun get(@PathVariable uuid: String): ResponseEntity<ApiResponse<BadugiGame>> {
        return ResponseEntity.ok(ApiResponse.success(badugiService.get(UUID.fromString(uuid))))
    }

    @PostMapping("/{uuid}/join")
    fun join(@PathVariable uuid: String): ResponseEntity<ApiResponse<*>> {
        val userIdFromHeader = 0L
        badugiService.join(UUID.fromString(uuid), userIdFromHeader)
        return ResponseEntity.ok(ApiResponse.success(null))
    }

    @PostMapping("/{uuid}/leave")
    fun exit(@PathVariable uuid: String): ResponseEntity<ApiResponse<*>> {
        val userIdFromHeader = 0L
        badugiService.exit(UUID.fromString(uuid), userIdFromHeader)
        return ResponseEntity.ok(ApiResponse.success(null))
    }
}