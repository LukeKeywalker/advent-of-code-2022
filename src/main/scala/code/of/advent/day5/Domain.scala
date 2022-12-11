package code.of.advent.day5

object Domain {
  type LoadingZone = Array[Array[Char]]
  object LoadingZone {
    def stack(items: String): Array[Char]        = items.toArray
    def apply(stacks: Array[Char]*): LoadingZone = Array(stacks: _*)
  }

  object Crane {
    sealed trait Model
    object Model {
      final case object CrateMover9000 extends Model
      final case object CrateMover9001 extends Model
    }
  }

  sealed trait Command
  object Command {
    final case class Move(amount: Int, f: Int, t: Int) extends Command {
      val from: Int = f - 1
      val to: Int   = t - 1
    }
    final case class Deliver(value: Char, t: Int) extends Command {
      val to: Int = t - 1
    }
  }

  case class Dock(craneModel: Crane.Model, loadingZone: LoadingZone) {
    import code.of.advent.day5.Domain.Crane.Model.{CrateMover9000, CrateMover9001}

    def cratesOnTop: String =
      loadingZone
        .map(_.headOption)
        .flatMap(_.fold(" ")(_.toString))
        .mkString

    def execute(cmd: Command.Move): Dock = {
      val movedCrates   = loadingZone(cmd.from).take(cmd.amount)
      val nextFromStack = loadingZone(cmd.from).drop(cmd.amount)
      val nextDestStack = ((craneModel match {
        case CrateMover9000 => movedCrates.reverse.toVector
        case CrateMover9001 => movedCrates.toVector
      }) ++ loadingZone(cmd.to).toVector).toArray

      Dock(
        craneModel,
        loadingZone
          .updated(cmd.from, nextFromStack)
          .updated(cmd.to, nextDestStack)
      )
    }
  }

  def toLoadingZoneLayer(line: String): List[Option[Char]] = {
    def nextMultipleOf(x: Int) = {
      line.length + (x - line.length % x)
    }

    line
      .padTo(nextMultipleOf(4), ' ')
      .sliding(size = 4, step = 4)
      .map {
        case window if window.startsWith("[") && window.endsWith("] ") => Some(window(1))
        case _                                                         => None
      }
      .toList
  }

  def parseLoadCommandSequence(input: Iterator[String]): LoadingZone = {
    val inputLines = input.takeWhile(x => !x.isBlank)
    val layerLoadCommandSequences = input.toList.reverse.map(toLoadingZoneLayer)

    Array.empty
  }
}
