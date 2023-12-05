package aoc.day01

import aoc.utils.*

@main def part1 = println(PuzzleInput(day = 1, part = 1, isExample = false).toVector.map(sumFirstLastDigit(_)).sum)