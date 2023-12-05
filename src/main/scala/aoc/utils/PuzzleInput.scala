package aoc.utils

object PuzzleInput:
	def parse(name: String): Vector[String] = 
		val fileName = s"input/$name.txt"
		scala.io.Source.fromFile(fileName).getLines().toVector