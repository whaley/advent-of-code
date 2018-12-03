package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object Day10Spec : Spek({
    given("Day Ten Part One") {
        class TestCase(val ints: IntArray, val lengths: IntArray, val expected: Int)
        val case = TestCase(intArrayOf(0, 1, 2, 3, 4), intArrayOf(3, 4, 1, 5), 12)

        on("knotHash") {
            assertEquals(case.expected, knotHash(case.ints, case.lengths))
        }
    }
})