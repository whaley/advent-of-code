package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object CalculatorSpec : Spek({
    given("A sample spec") {
        on("a dumb test") {
            it("should blithely pass...") {
                assert(true)
            }
        }
    }
})