package aoc.utils

object PuzzleInput:
	def parse(name: String): Vector[String] = 
		import scala.io.Source

		val fileName = s"input/$name.txt"
		Source.fromFile(fileName).getLines().toVector