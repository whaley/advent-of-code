package com.morninghacks.aoc2017

val GROUP_START_TOKEN = '{'
val GROUP_END_TOKEN = '}'
val GARBAGE_START_TOKEN = '<'
val GARBAGE_END_TOKEN = '>'
val ESCAPE_TOKEN = '!'

sealed class Container
class Garbage(val contents: String) : Container()
class Group(val contents: List<Container>): Container() {
    fun countGroups(): Int = 1 + contents.filterIsInstance<Group>().map(Group::countGroups).sum()
}


fun parseTree(input: String): Group {
    val iter: CharIterator = input.iterator()
    val garbageTextBuilder = StringBuilder()
    val groupContentsBuilder = mutableListOf<Container>()
    while (iter.hasNext()) {
        val current = iter.nextChar()

        when(current) {
            ESCAPE_TOKEN -> if (iter.hasNext()) iter.nextChar() //skip the next token since it is escaped
            GROUP_START_TOKEN -> ""
            GROUP_END_TOKEN -> ""
            GARBAGE_START_TOKEN -> ""
            GARBAGE_END_TOKEN -> ""
            else ->
        }

        if (current == ESCAPE_TOKEN) {

            continue
        }

        if (current = GROUP_START_TOKEN) {

        }
    }


}