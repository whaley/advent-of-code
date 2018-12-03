package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object Day07Spec : Spek({
    val input = """
pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)
        """

    given("Day Six : Part One and Two") {
        on("findRootNode") {
            val (rootId,p) = solve(input)
            it("") {
                assertEquals("tknk", rootId)
                assertEquals("ugml", p.first)
                assertEquals(60, p.second)

            }
        }
    }
})