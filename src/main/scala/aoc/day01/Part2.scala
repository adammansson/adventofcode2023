package aoc.day01

import aoc.utils.*

def replaceLettersWithDigit(s: String): String =
	s
		.replaceAll("one", "one1one")
		.replaceAll("two", "two2two")
		.replaceAll("three", "three3three")
		.replaceAll("four", "four4four")
		.replaceAll("five", "five5five")
		.replaceAll("six", "six6six")
		.replaceAll("seven", "seven7seven")
		.replaceAll("eight", "eight8eight")
		.replaceAll("nine", "nine9nine")


@main def part2 = println(PuzzleInput.toVector("day01/part1").map(s => sumFirstLastDigit(replaceLettersWithDigit(s))).sum)