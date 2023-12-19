package aoc.utils

case class PuzzleExample(day: Int, nbr: Int):
	import scala.io.Source

	private val body = Source.fromFile(s"input/day${if day < 10 then "0" else ""}$day/example$nbr.txt").mkString

	def toVector: Vector[String] =
		body.split("\n").toVector