package com.morninghacks.aoc2017

val GROUP_START_TOKEN = '{'
val GROUP_END_TOKEN = '}'
val GARBAGE_START_TOKEN = '<'
val GARBAGE_END_TOKEN = '>'
val ESCAPE_TOKEN = '!'

sealed class Container
class Garbage(val contents: String) : Container()
class Group(val contents: MutableList<Container>) : Container() {
    fun countGroups(): Int = 1 + contents.filterIsInstance<Group>().map(Group::countGroups).sum()
    fun score(level: Int = 1): Int = 1 + contents.filterIsInstance<Group>().map { g -> level + g.score(level + 1)}.sum()
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
                garbageTextBuilder = StringBuilder(); isGarbage = true
            }
            GARBAGE_END_TOKEN -> {
                groupBeingBuilt?.contents?.add(Garbage(garbageTextBuilder.toString())); isGarbage = false
            }
            else -> garbageTextBuilder.append(current)
        }
    }
    return groupBeingBuilt
}