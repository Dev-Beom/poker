package com.play.poker.game

interface RedisRepository {
    fun setnx(key: String, value: String): Boolean
    fun del(key: String): Long
    fun hget(key: String, field: String): String
    fun hset(key: String, field: String, value: String): Boolean
    fun hmget(key: String, hashKeys: List<String>): List<String?>
    fun hdel(key: String, hashKeys: List<String>): Long
    fun zadd(key: String, value: String, score: Double): Boolean
    fun zrangebyscore(key: String, min: Double, max: Double): String
    fun zrem(key: String, values: List<String>): Long
    fun rpush(key: String, value: String): Long
    fun rpush(key: String, values: List<String>): Long
    fun lrange(key: String, start: Long, end: Long): String
    fun ltrim(key: String, start: Long, end: Long): Boolean
    fun lpop(key: String): String?
    fun flushAll(): String
}