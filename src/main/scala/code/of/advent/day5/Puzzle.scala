package code.of.advent.day5

import code.of.advent.day5.Domain.{Crane, Dock, LoadingZone}
import code.of.advent.day5.Domain.LoadingZone.stack
import code.of.advent.day5.Implicits.StringToCraneCommand
import code.of.advent.day5.Puzzle.Variant
import code.of.advent.day5.Puzzle.Variant.{A, B}

import scala.io.Source

protected class Puzzle(val initialLoadingZone: LoadingZone, val variant: Variant) {
  def solution: String = solutionFor(Source.fromResource("input-day-5.txt").getLines())

  def solutionFor(input: Iterator[String]): String = {
    val craneModel = variant match {
      case A => Crane.Model.CrateMover9000
      case B => Crane.Model.CrateMover9001
    }
    val initialDockState = Dock(
      craneModel = craneModel,
      loadingZone = initialLoadingZone
    )
    val inputCraneCommands = input
      .dropWhile(!_.startsWith("move"))
      .flatMap(_.toMoveCommand)

    inputCraneCommands
      .foldLeft(initialDockState)((dockState, cmd) => dockState.execute(cmd))
      .cratesOnTop
  }
}

object Puzzle {
  sealed trait Variant
  object Variant {
    final case object A extends Variant
    final case object B extends Variant
  }

  val initialLoadingZone: LoadingZone = LoadingZone(
    stack("PGRN"),
    stack("CDGFLBTJ"),
    stack("VSM"),
    stack("PZCRSL"),
    stack("QDWCNLSP"),
    stack("SMDWNTC"),
    stack("PWGDH"),
    stack("VMCSHPLZ"),
    stack("ZGWLFPR")
  )

  def apply(variant: Variant): Puzzle = {
    new Puzzle(initialLoadingZone, variant)
  }

  def apply(loadingZone: LoadingZone, variant: Variant): Puzzle = {
    new Puzzle(loadingZone, variant)
  }

  def main(args: Array[String]): Unit = {
    println(Puzzle(A).solution)
    println(Puzzle(B).solution)
  }
}
