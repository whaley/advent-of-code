package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals


object Day03Spec : Spek({
    given("Day Three : Part One") {
        val testData = mapOf(1 to 0, 9 to 2, 10 to 3, 12 to 3, 23 to 2, 27 to 4, 33 to 4, 35 to 4, 40 to 3, 47 to 4, 48 to 5,1024 to 31)
        on("manhattan") {
            for ((input, expected) in testData) {
                it("should return $expected when given input of $input") {
                    assertEquals(expected, manhattan(input))
                }
            }
        }
    }
})

