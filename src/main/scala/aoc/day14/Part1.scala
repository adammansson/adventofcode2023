package aoc.day14

import aoc.utils.*

@main def part1 =
	val originalPlatform = PuzzleInput(14).toVector.map(_.toVector)
	val finalPlatform = originalPlatform.transpose.map(rocksMovedLeft).transpose

	println(totalLoad(finalPlatform))
