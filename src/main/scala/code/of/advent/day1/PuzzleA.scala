package code.of.advent.day1

import scala.io.Source

object PuzzleA {
  def solve(): Unit = {
    val input = Source.fromResource("input-day-1.txt")
    val zero  = (0, 0)
    val result = input
      .getLines()
      .foldLeft(zero) { (acc, x) =>
        x match {
          case "" => if (acc._2 > acc._1) (acc._2, 0) else (acc._1, 0)
          case _  => (acc._1, acc._2 + x.toInt)
        }
      }
      ._1
    println(result)
  }
}
