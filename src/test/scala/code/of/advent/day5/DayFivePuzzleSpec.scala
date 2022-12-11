package code.of.advent.day5

import code.of.advent.day5.Domain.{Command, Crane, Dock, LoadingZone}
import code.of.advent.day5.Domain.LoadingZone.stack
import code.of.advent.day5.Implicits.StringToCraneCommand
import code.of.advent.day5.Puzzle.Variant
import org.scalatest.flatspec.AnyFlatSpec

class DayFivePuzzleSpec extends AnyFlatSpec {
  "Input line 'move 2 from 4 to 6'" should "be parsed as Move(amount = 2, from = 4, to = 6)" in {
    val parsedCraneCommand = "move 2 from 4 to 6".toMoveCommand
    assert(parsedCraneCommand.isDefined)
    assert(parsedCraneCommand.get == Command.Move(2, 4, 6))
  }

  "Initial dock's top crates" should "be PCVPQSPVZ" in {
    assert(Dock(Crane.Model.CrateMover9000, Puzzle.initialLoadingZone).cratesOnTop === "PCVPQSPVZ")
  }

  "Example dock initialLoadingZone layer" should "be parsed correctly from line '    [D]'" in {
    assert(Domain.toLoadingZoneLayer("    [D]") === List(None, Some('D')))
  }

  "Example dock initialLoadingZone layer" should "be parsed correctly from line '[D]'" in {
    assert(Domain.toLoadingZoneLayer("[D]") === List(Some('D')))
  }

  "Example dock initialLoadingZone layer" should "be parsed correctly from line ''" in {
    assert(Domain.toLoadingZoneLayer("") === List(None))
  }

  "Example dock initialLoadingZone layer" should "be parsed correctly from line '[N] [C]'" in {
    assert(Domain.toLoadingZoneLayer("[N] [C]") === List(Some('N'), Some('C')))
  }

  "Example dock initialLoadingZone layer" should "be parsed correctly from line '[Z] [M] [P]'" in {
    assert(Domain.toLoadingZoneLayer("[Z] [M] [P]") === List(Some('Z'), Some('M'), Some('P')))
  }

  "Example dock initialLoadingZone layer" should "be parsed correctly from line '[Z]     [P]'" in {
    assert(Domain.toLoadingZoneLayer("[Z]     [P]") === List(Some('Z'), None, Some('P')))
  }

  "Example dock initialLoadingZone layer" should "be parsed correctly from line '    [M] [P]'" in {
    assert(Domain.toLoadingZoneLayer("    [M] [P]") === List(None, Some('M'), Some('P')))
  }

  "Example dock initialLoadingZone" should "be parsed correctly" in {
    val exampleInput =
      """    [D]
        |[N] [C]
        |[Z] [M] [P]
        |""".stripMargin.split('\n').iterator
    val exampleLoadCommandSequence = Domain.parseLoadCommandSequence(exampleInput)
    assert(
      exampleLoadCommandSequence === LoadingZone(
        "NZ".toArray,
        "DCM".toArray,
        "P".toArray
      )
    )
  }

  "Example dock's top crates" should "be CMZ" in {
    val exampleInput =
      """    [D]
        |[N] [C]
        |[Z] [M] [P]
        | 1   2   3
        |
        |move 1 from 2 to 1
        |move 3 from 1 to 3
        |move 2 from 2 to 1
        |move 1 from 1 to 2
        |""".stripMargin.split('\n').iterator
    val exampleLoadingZone = LoadingZone(
      stack("NZ"),
      stack("DCM"),
      stack("P")
    )
    assert(Puzzle(exampleLoadingZone, Variant.A).solutionFor(exampleInput) == "CMZ")
  }
}
