package aoc.utils

case class PuzzleExample(day: Int, part: Int):
	import scala.io.Source

	private val body = Source.fromFile(s"input/day0$day/example$part.txt").mkString

	def toVector: Vector[String] =
		body.split("\n").toVector