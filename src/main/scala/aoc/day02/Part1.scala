package aoc.day02

import aoc.utils.*

@main def part1 =
	val input = PuzzleInput.toVector("day02/part1")
	val config = Map("red" -> 12, "green" -> 13, "blue" -> 14)

	val subsets = parseSubsets(input)
	val result = subsets.map(game => game.exists(subset => subset.exists((color, count) => count > config(color)))).zipWithIndex.filterNot((value, _) => value).map((_, index) => index + 1).sum
	println(result)