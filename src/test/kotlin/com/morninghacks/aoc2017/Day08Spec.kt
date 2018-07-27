package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object Day08Spec : Spek({
    val input = """
        b inc 5 if a > 1
        a inc 1 if b < 5
        c dec -10 if a >= 1
        c inc -20 if c == 10
"""

    given("Day Eight : Part One") {
        on("parseInput") {
            val expected = listOf(
                    Instruction("b", 5, Condition("a", GreaterThan, 1)),
                    Instruction("a", 1, Condition("b", LessThan, 5)),
                    Instruction("c", 10, Condition("a", GreaterThanOrEqualTo, 1)),
                    Instruction("c", -20, Condition("c", EqualTo, 10))
            )
            it("input of $input should result in instruction list of $expected") {
                assertEquals(expected, parseInput(input))
            }
        }

        on("Input of $input") {
            it("should result in registers of {a:1, b:0, c:-20}") {
                assertEquals(mapOf("a" to 1, "b" to 0, "c" to -10), runInstructions(input))
            }
        }
    }

    given("Day Eight : Part Two") {
        on("") {
            it("") {

            }
        }
    }
})