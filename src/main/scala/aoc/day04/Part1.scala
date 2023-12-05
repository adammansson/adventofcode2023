package aoc.day04

import aoc.utils.* 

@main def part1 = 
	println(Card.parseMany(PuzzleInput.parse("day04/part1")).map(_.points).sum)