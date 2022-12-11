package code.of.advent.day4

import org.scalatest.flatspec.AnyFlatSpec

class DayFourPuzzleSpec extends AnyFlatSpec {
  "Input line '2-4,6-8'" should "decode to (Range(2, 4), Range(6, 8))" in {
    assert(PuzzleA.toRangePair("2-4,6-8") == (Range.inclusive(2, 4), Range.inclusive(6, 8)))
  }
}
