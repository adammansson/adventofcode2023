package aoc.day09

import aoc.utils.*

def extrapolateNext(xss: Vector[Vector[Int]]): Int =
	val placeholders = Array.fill(xss.length + 1)(0)

	for i <- xss.indices do
		placeholders(i + 1) = placeholders(i) + xss(i).last

	placeholders.last

@main def part1 =
	println(solve(PuzzleInput(9).toVector, extrapolateNext))