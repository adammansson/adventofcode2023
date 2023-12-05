package aoc.day02

import aoc.utils.*

@main def part2 =
	val input = PuzzleInput(2, 1, false).toVector

	val subsets = parseSubsets(input)
	val result = subsets.map(game => game.flatten.sortBy((_, count) => -count).distinctBy((color, _) => color).map((_, count) => count).foldLeft(1)((acc, value) => acc * value)).sum
	println(result)