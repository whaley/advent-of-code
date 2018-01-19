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
    println(createGridUntil(325489).values.max() ?: 0)
}


data class Point(val x: Int, val y: Int)


fun createGridUntil(target: Int) : Map<Point,Int>{
    val initial = Point(0, 0)
    val knownPointsAndScores = hashMapOf(initial to 1)
    var direction = Direction.RIGHT
    var currentPoint = initial
    var steps = 1
    var stepsRemainingBeforeTurn = steps

    while ((knownPointsAndScores[currentPoint] ?: 0) < target) {
        if (stepsRemainingBeforeTurn == 0 && (direction == Direction.UP || direction == Direction.DOWN)) {
            direction = direction.nextDirection()
            steps++
            stepsRemainingBeforeTurn = steps - 1
        } else if (stepsRemainingBeforeTurn == 0) {
            direction = direction.nextDirection()
            stepsRemainingBeforeTurn = steps - 1
        } else {
            stepsRemainingBeforeTurn--
        }

        currentPoint = direction.nextPoint(currentPoint)
        val score: Int = knownPointsAndScores.keys.intersect(adjacentPoints(currentPoint)).sumBy {
            knownPointsAndScores[it] ?: 0 }
        knownPointsAndScores[currentPoint] = score
    }
    return knownPointsAndScores
}

enum class Direction {
    UP {
        override fun nextDirection(): Direction = LEFT
        override fun nextPoint(point: Point) = Point(point.x, point.y + 1)
    },
    DOWN {
        override fun nextDirection(): Direction = RIGHT
        override fun nextPoint(point: Point) = Point(point.x, point.y - 1)

    },
    LEFT {
        override fun nextDirection(): Direction = DOWN
        override fun nextPoint(point: Point) = Point(point.x - 1, point.y)

    },
    RIGHT {
        override fun nextDirection(): Direction = UP
        override fun nextPoint(point: Point) = Point(point.x + 1, point.y)
    };

    abstract fun nextDirection(): Direction
    abstract fun nextPoint(point: Point): Point
}

fun adjacentPoints(point: Point): Set<Point> {
    val adjacentPoints = hashSetOf<Point>()
    for (x in -1..1) {
        for (y in -1..1) {
            adjacentPoints.add(Point(point.x + x, point.y + y))
        }
    }
    return adjacentPoints
}

