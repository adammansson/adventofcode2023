package aoc.day05

import aoc.utils.*

@main def part2 =
	val input = PuzzleInput.toString("day05/part1").split("\n\n").toVector.map(_.split("\n").toVector)
	val seedRanges = input(0)(0).split(": ").last.split(" ").map(_.toLong).toVector.sliding(2, 2).toVector
	val maps = input.tail.map(parseMap)

	var resMin = Long.MaxValue
	for seedRange <- seedRanges do
		for seed <- seedRange(0) until seedRange(0) + seedRange(1) do
			var source = seed
			var destination = applyMapping(maps(0), source)
			for i <- maps.indices.tail do
				source = destination
				destination = applyMapping(maps(i), source)

			if destination < resMin then resMin = destination
	println(resMin)

