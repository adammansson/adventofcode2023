package aoc.day08

import aoc.utils.*

@main def part2 =
	val input = PuzzleInput(8).toVector
	val instructions = parseInstructions(input.head)
	val graph = parseGraph(input.drop(2))

	val starting = graph.keys.filter(_(2) == 'A').toArray

	val cycleLengths = Array.fill(starting.length)(0L)

	for i <- starting.indices do
		var current = starting(i)

		var running = true
		var counter = 0L
		while running do
			current = graph(current).next(instructions((counter % instructions.length).toInt))
			counter += 1
			if current(2) == 'Z' then
				running = false
				cycleLengths(i) = counter

	println(cycleLengths.reduce(lcm))
