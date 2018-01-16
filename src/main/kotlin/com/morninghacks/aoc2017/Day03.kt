package com.morninghacks.aoc2017

import kotlin.math.roundToInt
import kotlin.math.sqrt


// let x be the target number
// let y next highest perfect square derived from multiplying odd numbers after and including x //
// let max distance = (square root of y) - 1

// let f(x) = max - ((y - x) % max)   <--- this fails for numbers like 10, 27, or 33
// if f(x) >= min then f(x) max - f(x)

fun manhattan(x: Int): Int {
    val y = nextOddPerfectSquareInclusive(x)
    val max = sqrt(y.toDouble()).roundToInt() - 1
    val min = max / 2
    val f = if (max == 0) 0 else max - ((y - x) % max) //for 33, f is 2, needs to be 4
    return if (f < min) max - f else f
}

fun nextOddPerfectSquareInclusive(x: Int): Int {
    for (i in 1..x step 2) {
        val iSquared = Math.pow(i.toDouble(), 2.toDouble()).roundToInt()
        val nextSquared = Math.pow((i + 2).toDouble(), 2.toDouble()).roundToInt()

        if (x == iSquared) return x
        else if (x > iSquared && x < nextSquared) return nextSquared
        else continue
    }
    throw IllegalStateException()
}

fun main(args: Array<String>) {
    println(manhattan(325489))
}