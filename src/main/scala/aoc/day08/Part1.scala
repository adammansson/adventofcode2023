package aoc.day08

import aoc.utils.*

@main def part1 =
	val input = PuzzleInput(8).toVector
	val instructions = parseInstructions(input.head)
	val graph = parseGraph(input.drop(2))

	var current = "AAA"
	var running = true
	var counter = 0
	while running do
		current = graph(current).next(instructions(counter % instructions.length))
		counter += 1
		running = current != "ZZZ"

	println(counter)
