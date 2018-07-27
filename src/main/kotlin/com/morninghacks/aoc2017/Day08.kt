package com.morninghacks.aoc2017

import java.lang.invoke.MethodHandles

/*
You receive a signal directly from the CPU. Because of your recent assistance with jump instructions, it would like you to compute the result of a series of unusual register instructions.

Each instruction consists of several parts: the register to modify, whether to increase or decrease that register's value, the amount by which to increase or decrease it, and a condition. If the condition fails, skip the instruction without modifying the register. The registers all start at 0. The instructions look like this:

b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10

These instructions would be processed as follows:

    Because a starts at 0, it is not greater than 1, and so b is not modified.
    a is increased by 1 (to 1) because b is less than 5 (it is 0).
    c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).
    c is increased by -20 (to -10) because c is equal to 10.

After this process, the largest value in any register is 1.

You might also encounter <= (less than or equal to) or != (not equal to). However, the CPU doesn't have the bandwidth to tell you what all the registers are named, and leaves that to you to determine.

What is the largest value in any register after completing the instructions in your puzzle input?
 */
sealed class ComparisonOperator {
    abstract fun apply(lhs: Int, rhs: Int): Boolean
}

object LessThan : ComparisonOperator() {
    override fun apply(lhs: Int, rhs: Int) = lhs < rhs
}

object LessThanOrEqualTo : ComparisonOperator() {
    override fun apply(lhs: Int, rhs: Int) = lhs <= rhs
}

object GreaterThan : ComparisonOperator() {
    override fun apply(lhs: Int, rhs: Int) = lhs > rhs
}

object GreaterThanOrEqualTo : ComparisonOperator() {
    override fun apply(lhs: Int, rhs: Int) = lhs >= rhs
}

object EqualTo : ComparisonOperator() {
    override fun apply(lhs: Int, rhs: Int) = lhs == rhs
}

object NotEqualTo : ComparisonOperator() {
    override fun apply(lhs: Int, rhs: Int) = lhs != rhs
}

data class Instruction(val registerName: String, val incValue: Int, val condition: Condition)
data class Condition(val registerName: String, val comparisonOperator: ComparisonOperator, val value: Int)
data class Results(val registers: Map<String, Int>, val highestSeenValue: Int)

fun runInstructions(input: String): Results {
    return runInstructions(parseInput(input))
}

fun runInstructions(instructions: List<Instruction>): Results {
    var highestSeenValue: Int = Int.MIN_VALUE
    val registers = mutableMapOf<String, Int>()
    for (instruction in instructions) {
        val cond = instruction.condition
        registers.putIfAbsent(instruction.registerName, 0)
        registers.putIfAbsent(cond.registerName, 0)
        if (cond.comparisonOperator.apply(registers.getOrDefault(cond.registerName, 0), cond.value)) {
            registers.merge(instruction.registerName, instruction.incValue, fun(orig, incBy): Int {
                //This is mildly disgusting, but gets the answer quickly
                val res = orig + incBy
                if (res > highestSeenValue) {
                    highestSeenValue = res
                }
                return res
            })
        }
    }
    return Results(registers, highestSeenValue)
}

fun parseInput(input: String): List<Instruction> {
    return input.lines()
            .filter(String::isNotBlank)
            .map(::lineToInstruction)

}

fun lineToInstruction(line: String): Instruction {
    val parts: List<String> = line.trim().split("""\s""".toRegex())
    val registerName: String = parts[0]
    val incBy: Int = if (parts[1] == "inc") parts[2].toInt() else parts[2].toInt() * -1
    val conditionRegisterName: String = parts[4]
    val conditionValue: Int = parts[6].toInt()
    val operator: ComparisonOperator = when (parts[5]) {
        "==" -> EqualTo
        "!=" -> NotEqualTo
        ">" -> GreaterThan
        ">=" -> GreaterThanOrEqualTo
        "<" -> LessThan
        "<=" -> LessThanOrEqualTo
        else -> throw IllegalArgumentException()
    }

    return Instruction(registerName, incBy, Condition(conditionRegisterName, operator, conditionValue))
}

fun main(args: Array<String>) {
    val input = MethodHandles.lookup().lookupClass().getResourceAsStream("/Day08Input.txt").bufferedReader().readText()
    val results = runInstructions(input)

    println("Part One Answer: ${results.registers.values.max()}")
    println("Part Two Answer: ${results.highestSeenValue}")
}







