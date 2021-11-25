package com.morninghacks

import scala.io.Source


object Day01 {
  def findCombination(numbers: Seq[Int], expected: Int, n: Int = 2):  Option[Seq[Int]] = {
    for (combo <- numbers.combinations(n)) {
      if (combo.sum == expected) return Some(combo)
    }
    None
  }

  def readFileLines(path: String): List[String] = {
    Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(path)).getLines.toList
  }
  
  def mult(ints: Option[Seq[Int]]) : Option[Int] = {
    ints match {
      case Some(xs) => Some(xs.fold(1)((x, y) => x * y))
      case _ => None
    }
  }


  def main(args: Array[String]): Unit = {
    val numbers = readFileLines("Day01.txt").map(_.toInt)
    val part1Answer = mult(findCombination(numbers, 2020, 2))
    val part2Answer = mult(findCombination(numbers, 2020, 3))
    println(part1Answer map (t => s"Part 1 Answer: $t") getOrElse ("Unable to determine part 1 answer"))
    println(part2Answer map (t => s"Part 2 Answer: $t") getOrElse ("Unable to determine part 2 answer"))
  }
}
