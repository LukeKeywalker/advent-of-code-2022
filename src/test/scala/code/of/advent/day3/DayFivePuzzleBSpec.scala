package code.of.advent.day3

import code.of.advent.day3.PuzzleB.Item
import org.scalatest.flatspec.AnyFlatSpec

class DayFivePuzzleBSpec extends AnyFlatSpec {

  "Input" should "be organized into groupRucksacks of 3" in {
    val input =
      """vJrwpWtwJgWrhcsFMMfFFhFp
        |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        |PmmdzqPrVvPwwTWBwg
        |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        |ttgJtRGJQctTZtZT
        |CrZsJsPPZsGzwwsLwLmpwMDw
        |""".stripMargin.split('\n').iterator
    assert(
      PuzzleB.groupingOf(input).toList ==  List(
        (
          "vJrwpWtwJgWrhcsFMMfFFhFp".map(Item).toList,
          "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL".map(Item).toList,
          "PmmdzqPrVvPwwTWBwg".map(Item).toList
        ),
        (
          "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn".map(Item).toList,
          "ttgJtRGJQctTZtZT".map(Item).toList,
          "CrZsJsPPZsGzwwsLwLmpwMDw".map(Item).toList
        )
      )
    )
  }

  "Badge of group holding items (List(Item(a), Item(b), Item(c)), List(Item(a), Item(t), Item(e)), List(Item(x), Item(a), Item(z))" should
    "be List(Item(a))" in {

    assert(PuzzleB.commonItemsOf(
      (
        List(Item('a'), Item('b'), Item('c')),
        List(Item('a'), Item('d'), Item('e')),
        List(Item('x'), Item('a'), Item('z'))
      )
    ) == List(Item('a')))
  }
}
