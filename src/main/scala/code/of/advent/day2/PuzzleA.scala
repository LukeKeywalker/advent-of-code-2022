package code.of.advent.day2

import scala.io.Source

object PuzzleA {

  val moveCodes: Map[String, Int] = Map(
    ("A", 0),
    ("B", 1),
    ("C", 2),
    ("X", 0),
    ("Y", 1),
    ("Z", 2)
  )

  def outcomeScoreOf(move: Int, response: Int): Int = (move, response) match {
    case (response, move) if win(response, move)  => 6
    case (response, move) if draw(response, move) => 3
    case _                                        => 0
  }

  private def draw(response: Int, move: Int) = {
    response == move
  }

  private def win(response: Int, move: Int) = {
    (response + 1) % 3 == move
  }

  def responseScore(response: Int): Int = response + 1

  def toScore(guideEntry: String): Int = {
    val (move, response) = toMoveResponse(guideEntry)
    val outcomeScore     = outcomeScoreOf(move, response)
    outcomeScore + responseScore(response)
  }

  private def toMoveResponse(move: String): (Int, Int) = {
    val moveResponse = move.split(' ').map(x => moveCodes(x))
    (moveResponse(0), moveResponse(1))
  }

  def solution: Int = {
    Source.fromResource("input-day-2.txt").getLines().map(toScore).sum
  }

  def main(args: Array[String]): Unit = {
    println(solution)
  }
}
