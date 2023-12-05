package aoc.day01

import aoc.utils.*

@main def part1 = println(PuzzleInput.toVector("day01/part1").map(sumFirstLastDigit(_)).sum)