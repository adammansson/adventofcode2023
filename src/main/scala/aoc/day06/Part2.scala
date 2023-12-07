package aoc.day06

import aoc.utils.*

@main def part2 =
	val input = PuzzleInput(6).toVector.map(_.filter(_.isDigit).toLong)
	val race = Race(input(0), input(1))

	val result = race.waysToBeatRecord
	println(result)