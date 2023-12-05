package aoc.day05

import aoc.utils.*

@main def part2 =
	println(Almanac.parse(PuzzleInput(5, 1, false).toString, true).minimumLocation)
