package com.morninghacks

import com.morninghacks.Day01.findCombination

import org.scalatest.funsuite._

class Day01Test extends AnyFunSuite {
  val numbers = Vector(1721, 979, 366, 299, 675, 1456)
  test("The Two Entries That Sum to 2020 in [1721, 979, 366, 299, 675, 1456] are 1721 and 299") {
    assert(findCombination(numbers,2020) == Some(Seq(1721, 299)))
  }

  test("The Three Entries That Sum to 2020 in [1721, 979, 366, 299, 675, 1456] are 979, 366, and 675") {
    assert(findCombination(numbers,2020,3) == Some(Seq(979, 366, 675)))
  }

  test("Hello should start with H") {
    assert("Hello".startsWith("H"))
  }
}