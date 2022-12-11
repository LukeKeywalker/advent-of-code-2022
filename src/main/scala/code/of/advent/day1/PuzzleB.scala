package code.of.advent.day1

import scala.io.Source

object PuzzleB {
  def solve(): Unit = {
    val input = Source.fromResource("input-day-1.txt")
    val zero  = (List(0, 0, 0), 0)
    val result = input
      .getLines()
      .foldLeft(zero) { (acc, x) =>
        x match {
          case "" => ((acc._2 :: acc._1).sorted(Ordering[Int].reverse).take(3), 0)
          case _  => (acc._1, acc._2 + x.toInt)
        }
      }
      ._1
      .sum
    println(result)
  }
}
