package aoc.day15

import aoc.utils.*

@main def part1 =
	val initSeq = PuzzleInput(15).toVector.head.split(",").map(parseInitStep).toVector

	val result = initSeq.map(_.hashCode).sum
	println(result)