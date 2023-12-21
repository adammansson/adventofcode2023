package aoc.day16

import aoc.utils.*
import scala.collection.mutable.ArrayBuffer

@main def part2 =
	val matrix = PuzzleInput(16).toVector.map(_.toVector)

	val fullRange = 0 until matrix.length
	val firstRange = 0 to 0
	val lastRange = (matrix.length - 1) to (matrix.length - 1)

	val result = Vector(
		(fullRange, lastRange, North),
		(firstRange, fullRange, East),
		(fullRange, firstRange, South),
		(lastRange, fullRange, West),
	).map((xs, ys, dir) => (for {x <- xs; y <- ys} yield tilesEnergized(matrix, Beam(Pos(x, y), dir))).max).max

	println(result)