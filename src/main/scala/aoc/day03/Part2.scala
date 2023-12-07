package aoc.day03

import aoc.utils.*

def findGearRatios(matrix: Vector[Vector[Char]]): Vector[Int] =
	var res = scala.collection.mutable.ArrayBuffer[Int]()

	for gearLocation <- findSymbolPositions(matrix, c => c == '*') do
		val adjacentNumbers = 
			for {
				(number, numberLocations) <- findNumberPositions(matrix)
				if !adjacentIndices(matrix, gearLocation).intersect(numberLocations).isEmpty
			} yield number

		if (adjacentNumbers.length == 2) then
			res.addOne(adjacentNumbers(0) * adjacentNumbers(1))
	res.toVector


@main def part2 =
	val matrix = PuzzleInput(3).toVector.map(_.toVector)

	val result = findGearRatios(matrix)
	println(result.sum)