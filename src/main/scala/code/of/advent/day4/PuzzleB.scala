package code.of.advent.day4

import scala.io.Source

object PuzzleB {
  def toRangePair(line: String): (Range.Inclusive, Range.Inclusive) = {
    val pairAssignments = line.split(',').map { rangeDescription =>
      val beginEnd = rangeDescription.split('-')
      Range.inclusive(beginEnd(0).toInt, beginEnd(1).toInt)
    }
    (pairAssignments(0), pairAssignments(1))
  }

  def solution: Int = {
    Source
      .fromResource("input-day-4.txt")
      .getLines()
      .map(toRangePair)
      .count { assignment =>
        assignment._1.intersect(assignment._2).nonEmpty
      }
  }

  def main(args: Array[String]): Unit = {
    println(solution)
  }
}
