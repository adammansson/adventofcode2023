package aoc.day04

import aoc.utils.* 

@main def part1 = 
	println(Card.parseMany(PuzzleInput(4, 1, false).toVector).map(_.points).sum)