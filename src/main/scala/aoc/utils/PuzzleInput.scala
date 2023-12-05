package aoc.utils

import scala.io.Source

case class PuzzleInput(day: Int, part: Int, isExample: Boolean):
	private val file = Source.fromFile(s"input/day0$day/${if isExample then s"example" else s"input"}$part.txt")

	def toVector: Vector[String] = 
		file.getLines.toVector

	override def toString: String =
		file.toVector.mkString