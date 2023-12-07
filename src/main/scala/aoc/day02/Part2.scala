package aoc.day02

import aoc.utils.*

@main def part2 =
	println(parseSubsets(PuzzleInput(2).toVector).map(game => game.flatten.sortBy((_, count) => -count).distinctBy((color, _) => color).map((_, count) => count).foldLeft(1)((acc, value) => acc * value)).sum)