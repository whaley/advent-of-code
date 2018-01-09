package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals


object Day02Spec : Spek({
    given("Day Two : Part One") {
        val spreadsheet = """5 1 9 5
                            .7 5 3
                            .2 4 6 8""".trimMargin(".")
        on("checksum") {
            assertEquals(18,checksum(spreadsheet))
        }

    }
})