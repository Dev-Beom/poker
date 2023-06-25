package com.play.poker.util.dsl

import com.play.poker.value.Card
import com.play.poker.value.Suit

infix fun Suit.at(number: Int): Card = Card(number, suit =  this)