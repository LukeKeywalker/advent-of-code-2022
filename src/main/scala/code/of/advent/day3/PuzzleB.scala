package code.of.advent.day3

import scala.collection.immutable.ArraySeq
import scala.io.Source

object PuzzleB {
  case class Item(name: Char) {
    val priority: Int = name match {
      case c if c.isLower => c.toInt - 'a'.toInt + 1
      case c if c.isUpper => c.toInt - 'A'.toInt + Item('z').priority + 1
    }
  }

  def commonItemsOf(groupRucksacks: (List[Item], List[Item], List[Item])): List[Item] =
    groupRucksacks._1.distinct.intersect(groupRucksacks._2.distinct.intersect(groupRucksacks._3.distinct))

  def groupingOf(input: Iterator[String]): Iterator[(List[Item], List[Item], List[Item])] = {
    input.grouped(3).map {
      case ArraySeq(a, b, c) => (a.map(Item).toList, b.map(Item).toList, c.map(Item).toList)
    }
  }

  def solution: Int = {
    val input = Source.fromResource("input-day-3.txt")
    groupingOf(input.getLines()).flatMap(commonItemsOf).map(_.priority).sum
  }

  def main(args: Array[String]): Unit = {
    println(solution)
  }
}
