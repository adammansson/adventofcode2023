package aoc.day04

import aoc.utils.*

@main def part2 =
	val cards = Card.parseMany(PuzzleInput(4).toVector)
	val copies = Array.fill(cards.length)(1)

	for {
		i <- cards.indices
		j <- i + 1 to i + cards(i).wins
	} do copies(j) += copies(i)

	println(copies.sum)
