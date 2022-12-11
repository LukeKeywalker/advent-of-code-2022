package code.of.advent.day3

import scala.io.Source

object PuzzleA {
  case class Item(name: Char) {
    val priority: Int = name match {
      case c if c.isLower => c.toInt - 'a'.toInt + 1
      case c if c.isUpper => c.toInt - 'A'.toInt + Item('z').priority + 1
    }
  }

  def commonItemsOf(compartments: (List[Item], List[Item])): List[Item] =
    compartments._1.distinct.intersect(compartments._2.distinct)

  def compartmentalize(inputLine: String): (List[Item], List[Item]) = {
    val items = inputLine.map(Item)
    (items.slice(0, items.length / 2).toList, items.slice(items.length / 2, items.length).toList)
  }

  def solution: Int = {
    Source
      .fromResource("input-day-3.txt")
      .getLines()
      .map(compartmentalize)
      .flatMap(commonItemsOf)
      .map(_.priority)
      .sum
  }

  def main(args: Array[String]): Unit = {
    println(solution)
  }
}
