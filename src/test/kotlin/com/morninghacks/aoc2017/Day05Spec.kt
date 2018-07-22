package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object Day05Spec : Spek({
    given("Day Five : Part One") {
        val testJumpList = listOf(0, 3, 0, 1, -3)
        val expectedSteps = 5

        on("calculateNumberOfJumps") {
            it("should return $expectedSteps when given jumpList of $testJumpList") {
                assertEquals(expectedSteps, calculateNumberOfJumps(testJumpList))
            }
        }
    }

    given("Day Five : Part Two") {
        on("drugs") {
            it("should be high") {
                //TODO: implement
            }
        }

    }
})
