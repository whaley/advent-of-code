package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals


object Day04Spec : Spek({
    given("Day Four : Part One") {
        val testData = mapOf("aa bb cc dd ee" to true,
                "aa bb cc dd aa" to false,
                "aa bb cc dd aaa" to true)

        on("noDuplicates") {
            for ((input, expected) in testData) {
                it("should return $expected when given input of $input") {
                    assertEquals(expected, noDuplicates(input))
                }
            }
        }
    }

    given("Day Four : Part Two") {
        val testData = mapOf("abcde fghij" to true,
                "abcde xyz ecdab" to false,
                "a ab abc abd abf abj" to true,
                "iiii oiii ooii oooi oooo" to true,
                "oiii ioii iioi iiio" to false)

        on("noAnagrams") {
            for ((input, expected) in testData) {
                it("should return $expected when given input of $input") {
                    assertEquals(expected, noAnagrams(input))
                }
            }
        }
    }
})

