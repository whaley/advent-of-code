package com.morninghacks.aoc2017

val GROUP_START_TOKEN = '{'
val GROUP_END_TOKEN = '}'
val GARBAGE_START_TOKEN = '<'
val GARBAGE_END_TOKEN = '>'
val ESCAPE_TOKEN = '!'

sealed class Container
class Garbage(val contents: String) : Container()
class Group(val contents: List<Container>) : Container() {
    fun countGroups(): Int = 1 + contents.filterIsInstance<Group>().map(Group::countGroups).sum()
}



fun parseTree(input: CharIterator): Group {
    val innerContainersForGroup = mutableListOf<Container>()
    var garbageTextBuilder = StringBuilder()
    var isGarbage = false
    while (input.hasNext()) {
        val current = input.nextChar()
        when (current) {
            ESCAPE_TOKEN -> if (input.hasNext()) input.nextChar() //skip the next token since it is escaped
            GROUP_START_TOKEN -> {
                if (isGarbage) garbageTextBuilder.append(current)
                else innerContainersForGroup.add(parseTree(input))
            }
            GROUP_END_TOKEN -> {
                if (isGarbage) garbageTextBuilder.append(current)
                else return Group(contents = innerContainersForGroup)
            }
            GARBAGE_START_TOKEN -> {
                garbageTextBuilder = StringBuilder(); isGarbage = true
            }
            GARBAGE_END_TOKEN -> {
                innerContainersForGroup.add(Garbage(garbageTextBuilder.toString())); isGarbage = false
            }
            else -> garbageTextBuilder.append(current)
        }
    }
    return Group(innerContainersForGroup)
}