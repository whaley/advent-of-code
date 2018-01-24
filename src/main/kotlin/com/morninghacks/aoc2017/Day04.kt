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
    return if (input.isEmpty()) false
    else {
        val words = input.split(Regex("""\s+"""))
        val wordsByLength: Map<Int, List<String>> = words.groupBy { it.length}
        return wordsByLength.values
                .map { word -> allPairs(word).firstOrNull { isAnagram(it.first,it.second) } }
                .none { it != null }
    }
}

fun isAnagram(a: String, b: String) : Boolean {
    if (a.isEmpty() || b.isEmpty()) return false

    val aChars = a.toCharArray()
    val bChars = b.toCharArray()
    aChars.sort()
    bChars.sort()

    return Arrays.equals(aChars, bChars)
}

fun countValidPassphrases(phrases: Iterable<String>, validator: (String) -> Boolean) : Int {
    return phrases.count { validator(it) }
}

fun main(args: Array<String>) {
    val count: Int = MethodHandles.lookup().lookupClass().getResourceAsStream("/Day04PartOneInput.txt")
            .bufferedReader().useLines { countValidPassphrases(it.asIterable(), ::noAnagrams) }
    println(count)
}