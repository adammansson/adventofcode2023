package aoc.day16

import aoc.utils.*
import scala.collection.mutable.ArrayBuffer

def maxFromEdge(matrix: Vector[Vector[Char]], xs: Range, ys: Range, dir: Dir): Long =
	(for {x <- xs; y <- ys} yield (x, y)).map((x, y) => tilesEnergized(matrix, Beam(Pos(x, y), dir))).max

@main def part2 =
	val matrix = PuzzleInput(16).toVector.map(_.toVector)

	val firstRange = 0 to 0
	val lastRange = (matrix.length - 1) to (matrix.length - 1)

	val result = Vector(
		maxFromEdge(matrix, matrix(0).indices, firstRange, South),
		maxFromEdge(matrix, matrix(0).indices, lastRange, North),
		maxFromEdge(matrix, firstRange, matrix.indices, East),
		maxFromEdge(matrix, lastRange, matrix.indices, West),
	).max

	println(result)