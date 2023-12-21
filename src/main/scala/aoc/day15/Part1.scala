package aoc.day15

import aoc.utils.*

@main def part1 =
	val initSeq = PuzzleInput(15).toVector.head.split(",").toVector
	println(initSeq.map(hash).sum)