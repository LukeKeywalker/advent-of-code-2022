package code.of.advent.day2

import scala.io.Source

object PuzzleB {
  sealed abstract class Move(val score: Int)
  final case object Rock     extends Move(1)
  final case object Paper    extends Move(2)
  final case object Scissors extends Move(3)

  sealed abstract class Outcome(val score: Int)
  final case object Win  extends Outcome(6)
  final case object Draw extends Outcome(3)
  final case object Loss extends Outcome(0)

  val outcomeCodes: Map[String, Outcome] = Map(
    ("X", Loss),
    ("Y", Draw),
    ("Z", Win)
  )

  val moveCodes: Map[String, Move] = Map(
    ("A", Rock),
    ("B", Paper),
    ("C", Scissors)
  )

  def winningResponseTo(move: Move): Move = move match {
    case Rock     => Paper
    case Paper    => Scissors
    case Scissors => Rock
  }

  def losingResponseTo(move: Move): Move = move match {
    case Rock     => Scissors
    case Paper    => Rock
    case Scissors => Paper
  }

  def drawResponseTo(move: Move): Move = move

  def scoreOf(moveOutcome: (Move, Outcome)): Int = {
    val (move, outcome) = moveOutcome
    outcome match {
      case Win  => Win.score + winningResponseTo(move).score
      case Draw => Draw.score + drawResponseTo(move).score
      case Loss => Loss.score + losingResponseTo(move).score
    }
  }

  def moveAndOutcomeFrom(inputLine: String): (Move, Outcome) = {
    val moveResponse = inputLine.split(' ')
    (moveCodes(moveResponse(0)), outcomeCodes(moveResponse(1)))
  }

  def solution: Int = {
    Source
      .fromResource("input-day-2.txt")
      .getLines()
      .map(line => scoreOf(moveAndOutcomeFrom(line)))
      .sum
  }

  def main(args: Array[String]): Unit = {
    println(solution)
  }
}
