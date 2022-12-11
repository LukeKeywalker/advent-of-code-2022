package code.of.advent.day3

import code.of.advent.day3.PuzzleA.Item
import org.scalatest.flatspec.AnyFlatSpec

class DayFivePuzzleASpec extends AnyFlatSpec {
  "abcABC" should "be split into (List(Item(a), Item(b), Item(c)), List(Item(A), Item(B), Item(C)))" in {
    assert(
      PuzzleA.compartmentalize("abcABC") == (
        List(Item('a'), Item('b'), Item('c')),
        List(Item('A'), Item('B'), Item('C'))
      )
    )
  }

  "Priority of Item(a)" should "be equal to 1" in {
    assert(Item('a').priority == 1)
  }

  "Priority of Item(z)" should "be equal to 26" in {
    assert(Item('z').priority == 26)
  }

  "Priority of Item(A)" should "be equal to 27" in {
    assert(Item('A').priority == 27)
  }

  "Priority of Item(Z)" should "be equal to 52" in {
    assert(Item('Z').priority == 52)
  }

  "Common item types of List(Item(a), Item(b), Item(c)) and List(Item(X), Item(a), Item(c))" should
    "be List(Item(a), Item(c))" in {
    assert(
      PuzzleA.commonItemsOf(
        List(Item('a'), Item('b'), Item('c')),
        List(Item('X'), Item('a'), Item('c'))
      ) == List(Item('a'), Item('c'))
    )
  }

  "Common item types of List(Item(a), Item(a), Item(b)) and List(Item(a), Item(a), Item(c))" should
    "be List(Item(a))" in {
    assert(
      PuzzleA.commonItemsOf(
        List(Item('a'), Item('a'), Item('c')),
        List(Item('a'), Item('a'), Item('b'))
      ) == List(Item('a'))
    )
  }
}
