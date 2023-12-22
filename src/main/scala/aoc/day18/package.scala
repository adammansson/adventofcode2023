package aoc.day18

enum Dir:
	case Up, Down, Left, Right
export Dir.*

case class DigInstruction(dir: Dir, steps: Int)

object DigInstruction:
	def parse(str: String): Option[DigInstruction] = str.split(" ").toVector match
		case Vector(dirStr, stepsStr, _) => 
			val dir = dirStr match
				case "U" => Up
				case "D" => Down
				case "L" => Left
				case _ => Right
			val steps = stepsStr.toInt

			Some(DigInstruction(dir, steps))
		case _ =>
			None

	def parseSwapped(str: String): Option[DigInstruction] =
		val hex = str.split(" ").last.drop(2).dropRight(1)

		val dir = hex.last match
			case '0' => Right
			case '1' => Down
			case '2' => Left
			case '3' => Up
		val steps = Integer.parseInt(hex.take(5), 16)

		Some(DigInstruction(dir, steps))
		
case class Pos(x: Int, y: Int):
	def +(dir: Dir): Pos = dir match
		case Up => Pos(x, y - 1)
		case Down => Pos(x, y + 1)
		case Left => Pos(x - 1, y)
		case Right => Pos(x + 1, y)

