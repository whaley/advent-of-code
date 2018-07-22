package com.morninghacks.aoc2017

data class AllocationResults(val stepsNeeded: Int,val resultantBank: List<Int>)

tailrec fun reallocate(banks: List<Int>, seen: MutableSet<List<Int>> = mutableSetOf()): AllocationResults {
    if (seen.contains(banks)) {
        return AllocationResults(seen.size,banks)
    }
    seen.add(banks)

    val (idx, value) = highestIndexWithValue(banks)
    val newBanks = banks.toMutableList()

    newBanks[idx] = 0
    var count = 0
    var nextIdx = idx + 1
    while (count < value) {
        if (nextIdx == newBanks.size) {
            nextIdx = 0
        }
        newBanks[nextIdx] = newBanks[nextIdx] + 1
        count++
        nextIdx++
    }


    return reallocate(newBanks, seen)

}

fun highestIndexWithValue(banks: List<Int>): Pair<Int, Int> {
    var largestIndex = 0
    var highestValue = Int.MIN_VALUE
    for ((idx, value) in banks.withIndex()) {
        if (value > highestValue) {
            largestIndex = idx
            highestValue = value
        }
    }
    return Pair(largestIndex, highestValue)
}

fun main(args: Array<String>) {
    val partOne = reallocate(listOf(2, 8, 8, 5, 4, 2, 3, 1, 5, 5, 1, 2, 15, 13, 5, 14))
    println(partOne.stepsNeeded)
    val partTwo = reallocate(partOne.resultantBank)
    println(partTwo.stepsNeeded)
}