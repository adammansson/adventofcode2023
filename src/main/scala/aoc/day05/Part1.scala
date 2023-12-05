package aoc.day05

import aoc.utils.*

@main def part1 =
	val input = PuzzleInput.toString("day05/part1").split("\n\n").toVector.map(_.split("\n").toVector)
	val seeds = input(0)(0).split(": ").last.split(" ").map(_.toLong).toVector
	val maps = input.tail.map(parseMap)

	var resMin = Long.MaxValue
	for seed <- seeds do
		var source = seed
		var destination = applyMapping(maps(0), source)
		for i <- maps.indices.tail do
			source = destination
			destination = applyMapping(maps(i), source)


		if destination < resMin then resMin = destination

	println(resMin)
