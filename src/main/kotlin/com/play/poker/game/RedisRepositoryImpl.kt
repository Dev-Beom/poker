package com.play.poker.game

class RedisRepositoryImpl : RedisRepository {
    override fun setnx(key: String, value: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun del(key: String): Long {
        TODO("Not yet implemented")
    }

    override fun hget(key: String, field: String): String {
        TODO("Not yet implemented")
    }

    override fun hset(key: String, field: String, value: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun hmget(key: String, hashKeys: List<String>): List<String?> {
        TODO("Not yet implemented")
    }

    override fun hdel(key: String, hashKeys: List<String>): Long {
        TODO("Not yet implemented")
    }

    override fun zadd(key: String, value: String, score: Double): Boolean {
        TODO("Not yet implemented")
    }

    override fun zrangebyscore(key: String, min: Double, max: Double): String {
        TODO("Not yet implemented")
    }

    override fun zrem(key: String, values: List<String>): Long {
        TODO("Not yet implemented")
    }

    override fun rpush(key: String, value: String): Long {
        TODO("Not yet implemented")
    }

    override fun rpush(key: String, values: List<String>): Long {
        TODO("Not yet implemented")
    }

    override fun lrange(key: String, start: Long, end: Long): String {
        TODO("Not yet implemented")
    }

    override fun ltrim(key: String, start: Long, end: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun lpop(key: String): String? {
        TODO("Not yet implemented")
    }

    override fun flushAll(): String {
        TODO("Not yet implemented")
    }
}