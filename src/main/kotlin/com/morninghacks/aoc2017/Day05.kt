package com.morninghacks.aoc2017

import java.lang.invoke.MethodHandles

tailrec fun calculateNumberOfJumps(jumpList: List<Int>, position: Int = 0, step: Int = 0): Int {
    if (position >= jumpList.size) {
        return step
    }
    val newPos = jumpList[position] + position
    val updatedList = jumpList.mapIndexed {idx,value -> if (idx == position) value + 1 else value}
    return calculateNumberOfJumps(updatedList, newPos, step + 1)
}

fun main(args: Array<String>) {
    val jumpList = MethodHandles.lookup().lookupClass().getResourceAsStream("/Day05Input.txt")
            .bufferedReader().lineSequence().map { s -> s.toInt() }.toList()
    println(calculateNumberOfJumps(jumpList))
}