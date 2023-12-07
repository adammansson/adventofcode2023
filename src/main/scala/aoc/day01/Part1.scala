package aoc.day01

import aoc.utils.*

@main def part1 = println(PuzzleInput(day = 1).toVector.map(sumFirstLastDigit(_)).sum)