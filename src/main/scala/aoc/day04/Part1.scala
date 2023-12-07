package aoc.day04

import aoc.utils.* 

@main def part1 = 
	println(Card.parseMany(PuzzleInput(4).toVector).map(_.points).sum)