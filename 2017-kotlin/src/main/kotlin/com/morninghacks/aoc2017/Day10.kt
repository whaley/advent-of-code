package com.morninghacks.aoc2017

import java.util.*

fun knotHash(ints: IntArray, lengths: IntArray) : Int {
    var currentIdx = 0
    var skipSize = 0

    fun nextCurrentIdx(length: Int): Int {
        val absoluteIdx = currentIdx + length + skipSize
        return absoluteIdx % ints.size
    }

    fun endIdxOfSubList(length: Int) : Int {
        val absoluteIdx = currentIdx + length - 1
        return absoluteIdx % ints.size
    }

    for (length in lengths) {
        val endIdx = endIdxOfSubList(length)
        val values = ArrayDeque<Int>()

        if (currentIdx < endIdx) {
            for (i in currentIdx..endIdx) {
                values.push(ints[i])
            }
        } else {
            for (i in currentIdx..ints.size) {
                values.push(ints[i])
            }
            for (i in 0..endIdx) {
                values.push(ints[i])
            }
        }

        if (currentIdx < endIdx) {
            for (i in currentIdx..endIdx) {
                ints[i] = values.pop()
            }
        } else {
            for (i in currentIdx..ints.size) {
                ints[i] = values.pop()
            }
            for (i in 0..endIdx) {
                ints[i] = values.pop()
            }
        }

        currentIdx = nextCurrentIdx(length)
        skipSize++
    }

    return ints[0] * ints[1]
}

