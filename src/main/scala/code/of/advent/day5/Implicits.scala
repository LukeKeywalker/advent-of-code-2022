package code.of.advent.day5

import code.of.advent.day5.Domain.Command

object Implicits {
  implicit class StringToCraneCommand(s: String) {
    def toMoveCommand: Option[Command.Move] =
      try {
        val integers = s.split(' ').flatMap(_.toIntOption)
        Some(Command.Move(integers(0), integers(1), integers(2)))
      } catch {
        case _: Throwable => None
      }
  }
}
