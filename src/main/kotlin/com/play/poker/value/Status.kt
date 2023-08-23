package com.play.poker.value

enum class GameStatus {
    READY, IN_GAME
}

enum class UserStatus {
    READY, IN_GAME, DIED
}


/**
 * @property CALL 앞사람의 베팅 금액과 동일한 베팅 금액 베팅
 * @property STAKE 기본 판돈 만큼만 베팅 (boss 만 가능)
 * @property DOUBLE 앞사람이 베팅한 금액의 2배를 베팅
 * @property HALF 전체 판돈의 절반을 베팅
 * @property DIE 새로 베팅하지 않고 이번 판을 포기
 * @property CHECK 머니를 베팅하지 않고 다음 카드를 받음 (boss 만 가능)
 * @property QUARTER 전체 판돈의 1/4를 베팅
 * @property FULL 전테 판돈 만큼, 판돈의 100%를 베팅
 * @property MAX 자신의 베팅 한도내의 최대 베팅
 * @property NONE 아무 상태도 아닌 대기
 */
enum class Betting {
    CALL,
    STAKE,
    DOUBLE,
    HALF,
    DIE,
    CHECK,
    QUARTER,
    FULL,
    MAX,
    NONE
}
