package com.play.poker.game.badugi.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BadugiGameRedisRepository : CrudRepository<BadugiGame, String> {

}
