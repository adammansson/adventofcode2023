package aoc.day09

import aoc.utils.*

def extrapolatePrevious(xss: Vector[Vector[Int]]): Int =
	val placeholders = Array.fill(xss.length + 1)(0)

	for i <- xss.indices do
		placeholders(i + 1) = xss(i).head - placeholders(i)

	placeholders.last

@main def part2 =
	println(solve(PuzzleInput(9).toVector, extrapolatePrevious))
