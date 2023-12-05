package aoc.utils

import scala.io.{Source, BufferedSource}

object PuzzleInput:
	private def getFile(name: String): BufferedSource =
		val fileName = s"input/$name.txt"
		Source.fromFile(fileName)

	def toVector(name: String): Vector[String] = 
		getFile(name).getLines.toVector

	def toString(name: String): String =
		getFile(name).toVector.mkString