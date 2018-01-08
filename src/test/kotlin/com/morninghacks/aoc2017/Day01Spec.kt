package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals


object Day01Spec : Spek({
    given("Day One : Part One") {
        val dataProvider = mapOf("1122" to 3,
                "1111" to 4,
                "1234" to 0,
                "91212129" to 9)


        on("captcha") {
            for ((input, expected) in dataProvider) {
                it("should produce $expected") {
                    assertEquals(expected, captcha(input))
                }
            }
        }

        on("functionalCaptcha") {
            for ((input, expected) in dataProvider) {
                it("should produce $expected") {
                    assertEquals(expected, functionalCaptcha(input))
                }
            }
        }
    }

    given("Day One : Part Two") {
        val dataProvider = mapOf("1212" to 6,
                "1221" to 0,
                "123425" to 4,
                "123123" to 12,
                "12131415" to 4)

        on("captcha") {
            for ((input, expected) in dataProvider) {
                it("should produce $expected") {
                    assertEquals(expected, captcha(input, input.length / 2))
                }
            }
        }
    }
})



