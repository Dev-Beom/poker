package com.play.poker.game.badugi

import com.fasterxml.jackson.databind.ObjectMapper
import com.play.poker.game.GameService
import com.play.poker.game.badugi.domain.BadugiGame
import com.play.poker.game.badugi.domain.BadugiGameRedisRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import java.util.*

/**
 * API / Socket Gateway(controller) <-> Badugi Service for Web Service
 */
@Service
class BadugiService(
//    private val badugiGameRedisRepository: BadugiGameRedisRepository,
    @Qualifier("badugiCardStoreRedisTemplate") private val redisTemplate: RedisTemplate<String, BadugiGame>,
    private val mapper: ObjectMapper
) : GameService {
    init {
        val uuid = create(1)
        println(uuid)
        get(uuid)
    }

    override fun create(hostUserId: Long): UUID {
        val uuid = UUID.randomUUID()
        val game = BadugiGame.of(uuid, hostUserId)
        println("uuid: ${uuid}")
        println("game: ${mapper.writeValueAsString(game)}")
//        redisTemplate.opsForValue().set(
//            uuid.toString(),
//            mapper.writeValueAsString(game)
//        )
        redisTemplate.opsForValue().set(
            uuid.toString(),
            game
        )
        return uuid
    }

    final fun get(uuid: UUID) {
        val ops: ValueOperations<String, BadugiGame> = redisTemplate.opsForValue()
        println(ops[uuid.toString()]?.cardStore?.cards?.forEach {
            println(it)
        })
    }

    override fun join(uuid: UUID) {
        TODO("Not yet implemented")
    }

    @Modifying(clearAutomatically = true)
    override fun exit(uuid: UUID, userId: Long) {
        TODO("Not yet implemented")
    }
}
