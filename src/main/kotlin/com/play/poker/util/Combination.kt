package com.play.poker.util

object Combination {
    fun <T> combinations(elements: List<T>, r: Int): List<List<T>> {
        val combinations = mutableListOf<List<T>>()
        val temp = mutableListOf<T>()

        fun backtrack(start: Int, temp: MutableList<T>) {
            if (temp.size == r) {
                combinations.add(temp.toList())
                return
            }

            for (i in start until elements.size) {
                temp.add(elements[i])
                backtrack(i + 1, temp)
                temp.removeAt(temp.size - 1)
            }
        }

        backtrack(0, temp)
        return combinations
    }
}