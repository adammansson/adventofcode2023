package aoc.day01

import aoc.utils.*

@main def part1 = println(PuzzleInput.parse("day01/part1and2").map(sumFirstLastDigit(_)).sum)