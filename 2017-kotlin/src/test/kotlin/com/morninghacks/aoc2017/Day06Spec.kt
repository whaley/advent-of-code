package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object Day06Spec : Spek({
    val inputBanks = listOf(0, 2, 7, 0)

    given("Day Six : Part One") {
        on("reallocate") {
            val expectedResult = AllocationResults(5, listOf(2,4,1,2))
            it("An initial bank of $inputBanks should return $expectedResult ") {
                assertEquals(expectedResult, reallocate(inputBanks))
            }
        }
    }

    given("Day Six : Part Two") {
        on("reallocate(reallocate().)") {
            it("") {
                val expectedResult = AllocationResults(4, listOf(2,4,1,2))
                assertEquals(expectedResult, reallocate(reallocate(inputBanks).resultantBank))
            }
        }
    }
})