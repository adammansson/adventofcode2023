package aoc.day06

import aoc.utils.*

@main def part1 =
	val input = PuzzleInput(6).toVector.map(_.split(":").last).map(_.split(" ").filterNot(_.isBlank).map(_.toLong).toVector)
	val races = input(0).zip(input(1)).map(Race.apply)
	
	val result = races.map(_.waysToBeatRecord).foldLeft(1L)((acc, value) => acc * value)
	println(result)