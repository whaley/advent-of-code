package com.morninghacks.aoc2017

import java.lang.invoke.MethodHandles
import java.util.*

fun noDuplicates(input: String): Boolean {
    return if (input.isEmpty()) false
    else {
        val words = input.split(Regex("""\s+"""))
        words.size == HashSet(words).size
    }
}

fun noAnagrams(input: String): Boolean {
//    return if (input.isEmpty()) false
//    else {
//        val words = input.split(Regex("""\s+"""))
//        val byLength: Map<Int, List<String>> = words.groupBy { it.length}
//        for (list in byLength.values) {
//            allPairs(list).firstOrNull { it. }
//        }
//        return true
//    }
    return false
}

fun isAnagram(a: String, b: String) : Boolean {
    val aChars = a.toCharArray()
    val bChars = b.toCharArray()
    aChars.sort()
    bChars.sort()
    return Arrays.equals(aChars, bChars)
}

fun countValidPassphrases(phrases: Iterable<String>) : Int = phrases.count { noDuplicates(it) }

fun main(args: Array<String>) {
    val count: Int = MethodHandles.lookup().lookupClass().getResourceAsStream("/Day04PartOneInput.txt")
            .bufferedReader().useLines { countValidPassphrases(it.asIterable()) }
    println(count)
}