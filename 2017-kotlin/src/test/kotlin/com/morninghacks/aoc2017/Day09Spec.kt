package com.morninghacks.aoc2017

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

/*
Here are some examples of whole streams and the number of groups they contain:

    {}, 1 group.
    {{{}}}, 3 groups.
    {{},{}}, also 3 groups.
    {{{},{},{{}}}}, 6 groups.
    {<{},{},{{}}>}, 1 group (which itself contains garbage).
    {<a>,<a>,<a>,<a>}, 1 group.
    {{<a>},{<a>},{<a>},{<a>}}, 5 groups.
    {{<!>},{<!>},{<!>},{<a>}}, 2 groups (since all but the last > are canceled).

Your goal is to find the total score for all groups in your input. Each group is assigned a score which is one more than the score of the group that immediately contains it. (The outermost group gets a score of 1.)

    {}, score of 1.
    {{{}}}, score of 1 + 2 + 3 = 6.
    {{},{}}, score of 1 + 2 + 2 = 5.
    {{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16.
    {<a>,<a>,<a>,<a>}, score of 1.
    {{<ab>},{<ab>},{<ab>},{<ab>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
    {{<!!>},{<!!>},{<!!>},{<!!>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
    {{<a!>},{<a!>},{<a!>},{<ab>}}, score of 1 + 2 = 3.

Now, you're ready to remove the garbage.

To prove you've removed it, you need to count all of the characters within the garbage. The leading and trailing < and > don't count, nor do any canceled characters or the ! doing the canceling.

    <>, 0 characters.
    <random characters>, 17 characters.
    <<<<>, 3 characters.
    <{!>}>, 2 characters.
    <!!>, 0 characters.
    <!!!>>, 0 characters.
    <{o"i!a,<{i<a>, 10 characters.


 */

object Day09Spec : Spek({
    given("Day Nine") {
        on("parseTree") {
            data class Case(val input: String, val value: Int)
            val countGroupsCases = listOf(
                    Case("{}", 1),
                    Case("{{{}}}", 3),
                    Case("{{},{}}", 3),
                    Case("{{{},{},{{}}}}", 6),
                    Case("{<{},{},{{}}>}", 1),
                    Case("{<a>,<a>,<a>,<a>}", 1),
                    Case("{{<a>},{<a>},{<a>},{<a>}}", 5),
                    Case("{{<!>},{<!>},{<!>},{<a>}}", 2)
            )

            for (case in countGroupsCases) {
                it("input of ${case.input} should result in a total ${case.value} groups") {
                    assertEquals(case.value, parseTree(case.input.iterator())?.countGroups())
                }
            }

            val scoreCases = listOf(
                    Case("{}", 1),
                    Case("{{{}}}", 6),
                    Case("{{},{}}", 5),
                    Case("{{{},{},{{}}}}", 16),
                    Case("{<a>,<a>,<a>,<a>}", 1),
                    Case("{{<ab>},{<ab>},{<ab>},{<ab>}}", 9),
                    Case("{{<!!>},{<!!>},{<!!>},{<!!>}}", 9),
                    Case("{{<a!>},{<a!>},{<a!>},{<ab>}}", 3)
            )
            for (case in scoreCases) {
                it("input of ${case.input} should result in a score of ${case.value}") {
                    assertEquals(case.value, parseTree(case.input.iterator())?.score())
                }
            }

            val numberOfCharacterCases = listOf(
                    Case("{<>}", 0),
                    Case("{<random characters>}", 17),
                    Case("{<<<<>}", 3),
                    Case("{<{!>}>}", 2),
                    Case("{<!!>}", 0),
                    Case("{<!!!>>}", 0),
                    Case("{<{o\"i!a,<{i<a>}", 10)
            )

            for (case in numberOfCharacterCases) {
                it("input of ${case.input} should result in a total number of characters of ${case.value}") {
                    assertEquals(case.value, parseTree(case.input.iterator())?.totalCharacters())
                }
            }

        }


    }
})

