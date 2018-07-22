package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object Day05Spec : Spek({
    val testJumpList = listOf(0, 3, 0, 1, -3)

    given("Day Five : Part One") {

        on("calculateNumberOfJumps when incrementing") {
            val expectedSteps = 5
            it("should return $expectedSteps when given jumpList of $testJumpList") {
                assertEquals(expectedSteps, calculateNumberOfJumps(testJumpList))
            }
        }
    }

    given("Day Five : Part Two") {
        on("calculateNumberOfJumps when increment, unless greater than 3, then decrement") {
            val expectedSteps = 10
            val postJumpAction = {i: Int -> if (i >= 3) i - 1 else i + 1}

            it("should be high") {
                assertEquals(expectedSteps, calculateNumberOfJumps(testJumpList,postJumpAction))
            }
        }

    }
})
