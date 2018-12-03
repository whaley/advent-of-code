package com.morninghacks.aoc2017

import java.lang.invoke.MethodHandles

const val GROUP_START_TOKEN = '{'
const val GROUP_END_TOKEN = '}'
const val GARBAGE_START_TOKEN = '<'
const val GARBAGE_END_TOKEN = '>'
const val ESCAPE_TOKEN = '!'

sealed class Container
class Garbage(val contents: String) : Container()
class Group(val contents: MutableList<Container>) : Container() {
    fun countGroups(): Int = 1 + contents.filterIsInstance<Group>().map(Group::countGroups).sum()
    fun score(level: Int = 1): Int = 1 + contents.filterIsInstance<Group>().map { g -> level + g.score(level + 1)}.sum()
    fun totalCharacters(): Int = contents.map(fun(container: Container) : Int {
        return when (container) {
            is Garbage -> container.contents.length
            is Group -> container.totalCharacters()
        }
    }).sum()
}

fun parseTree(input: CharIterator, groupBeingBuilt: Group? = null): Group? {
    var garbageTextBuilder = StringBuilder()
    var isGarbage = false
    while (input.hasNext()) {
        val current = input.nextChar()
        when (current) {
            ESCAPE_TOKEN -> if (input.hasNext()) input.nextChar() //skip the next token since it is escaped
            GROUP_START_TOKEN -> {
                if (isGarbage) garbageTextBuilder.append(current)
                else {
                    if (groupBeingBuilt == null) return parseTree(input, Group(mutableListOf()))
                    else groupBeingBuilt.contents.add(parseTree(input, Group(mutableListOf()))!!)
                }
            }
            GROUP_END_TOKEN -> {
                if (isGarbage) garbageTextBuilder.append(current)
                else return groupBeingBuilt
            }
            GARBAGE_START_TOKEN -> {
                if (isGarbage) garbageTextBuilder.append(current)
                else {
                    garbageTextBuilder = StringBuilder()
                    isGarbage = true
                }
            }
            GARBAGE_END_TOKEN -> {
                groupBeingBuilt?.contents?.add(Garbage(garbageTextBuilder.toString()))
                isGarbage = false
            }
            else -> garbageTextBuilder.append(current)
        }
    }
    return groupBeingBuilt
}

fun main(args: Array<String>) {
    val input = MethodHandles.lookup().lookupClass().getResourceAsStream("/Day09Input.txt")
            .bufferedReader().readText().trim()
    val group = parseTree(input.iterator())
    println("Score of Day 09 Input For All Groups is ${group?.score()}")
    println("Total number of characters in Day 09 Input is ${group?.totalCharacters()}")
}