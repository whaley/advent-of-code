package com.morninghacks.aoc2017

tailrec fun calculateNumberOfJumps(jumpList: List<Int>, position: Int = 0, step: Int = 0): Int {
    if (position >= jumpList.size) {
        return step
    }
    val newPos = jumpList[position] + position
    val updatedList = jumpList.mapIndexed {idx,value -> if (idx == position) value + 1 else value}
    return calculateNumberOfJumps(updatedList, newPos, step + 1)
}