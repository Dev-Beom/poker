package com.play.poker.game.badugi

import com.play.poker.common.CommonException
import com.play.poker.game.GameService
import com.play.poker.game.badugi.domain.BadugiGame
import com.play.poker.game.badugi.domain.BadugiUser
import com.play.poker.value.Betting
import com.play.poker.value.GameStatus
import com.play.poker.value.UserStatus
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * API / Socket Gateway(controller) <-> Badugi Service for Web Service
 */
@Service
class BadugiService(
    @Qualifier("badugiCardStoreRedisTemplate") private val gameManager: RedisTemplate<String, BadugiGame>,
) : GameService {
//    init {
//        val uuid = create(1)
//        println(uuid)
//        val game = get(uuid)
//    }

    override fun create(hostUserId: Long): UUID {
        val uuid = UUID.randomUUID()
        val game = BadugiGame.of(uuid, hostUserId)
        gameManager.opsForValue().set(
            uuid.toString(),
            game
        )
        return uuid
    }

    override fun get(uuid: UUID): BadugiGame {
        return gameManager.opsForValue().get(uuid.toString()) ?: throw CommonException.GAME_NOT_FOUND
    }

    @Transactional
    override fun join(uuid: UUID, userId: Long) {
        val game = gameManager.opsForValue().get(uuid.toString()) ?: throw CommonException.GAME_NOT_FOUND
        if (game.users.isMaxUser()) throw CommonException.GAME_USERS_FULL
        if (game.isNotReady()) throw CommonException.GAME_IS_IN_GAME
        val newUser = BadugiUser.of(userId)
        game.users.add(newUser)
        gameManager.opsForValue().set(
            uuid.toString(),
            game
        )
    }

    @Transactional
    override fun exit(uuid: UUID, userId: Long) {
        val game = gameManager.opsForValue().get(uuid.toString()) ?: throw CommonException.GAME_NOT_FOUND
        val exitUser = game.users.find { user -> user.userId == userId } ?: throw CommonException.USER_NOT_FOUND_IN_GAME
        if (exitUser.isInGame() || exitUser.isInGameByBetStatus()) throw CommonException.USER_CANT_OUT_GAME
        game.users.removeIf { it.userId == userId }
        gameManager.opsForValue().set(
            uuid.toString(),
            game
        )
    }

    private fun List<BadugiUser>.isMaxUser(): Boolean {
        val MAX_USER_COUNT = 5
        return this.size >= MAX_USER_COUNT
    }

    private fun BadugiGame.isNotReady(): Boolean {
        return this.status != GameStatus.READY
    }

    private fun BadugiUser.isInGameByBetStatus(): Boolean {
        return this.bet != Betting.DIE && this.bet != Betting.NONE
    }

    private fun BadugiUser.isInGame(): Boolean {
        return this.status == UserStatus.IN_GAME
    }
}
