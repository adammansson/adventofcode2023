package aoc.day03

import aoc.utils.*

def findPartNumbers(matrix: Vector[Vector[Char]]): Vector[Int] =
	var res = scala.collection.mutable.ArrayBuffer[Int]()

	for symbolLocation <- findSymbolPositions(matrix, c => !c.isDigit && c != '.') do
		val adjacentNumbers = 
			for {
				(number, numberLocations) <- findNumberPositions(matrix)
				if !adjacentIndices(matrix, symbolLocation).intersect(numberLocations).isEmpty
			} yield number

		res.addAll(adjacentNumbers)

	res.toVector

@main def part1 =
	val matrix = PuzzleInput.parse("day03/part1").map(_.toVector)

	val result = findPartNumbers(matrix)
	println(result.sum)
