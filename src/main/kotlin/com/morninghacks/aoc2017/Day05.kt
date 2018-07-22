package com.morninghacks.aoc2017

import java.lang.invoke.MethodHandles

fun calculateNumberOfJumps(jumpList: List<Int>, postJumpAction : (Int) -> Int = ::incAction, position: Int = 0, step: Int = 0): Int {
    tailrec fun loop(jumpList: MutableList<Int>, position: Int = 0, step: Int = 0) : Int {
        if (position >= jumpList.size) {
            return step
        }

        val newPosition = position + jumpList[position]
        jumpList[position] = postJumpAction(jumpList[position])
        return loop(jumpList, newPosition, step + 1)
    }

    return loop(jumpList.toMutableList())
}

private fun incAction(i: Int) : Int = i + 1

fun main(args: Array<String>) {
    val jumpList = MethodHandles.lookup().lookupClass().getResourceAsStream("/Day05Input.txt")
            .bufferedReader().lineSequence().map { s -> s.toInt() }.toList()
    println("Part One Answer: ${calculateNumberOfJumps(jumpList, postJumpAction = {i -> i + 1})}")
    println("Part Two Answer: ${calculateNumberOfJumps(jumpList, postJumpAction = {i -> if (i >= 3) i - 1 else i + 1})}")

}