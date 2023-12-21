package aoc.day16

import aoc.utils.*
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet

@main def part1 =
	val matrix = PuzzleInput(16).toVector.map(_.toVector)

	val result = tilesEnergized(matrix, Beam(Pos(0, 0), East))
	println(result)