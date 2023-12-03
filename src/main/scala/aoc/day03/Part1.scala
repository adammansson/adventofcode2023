package aoc.day03

import aoc.utils.*

def findNumbers(matrix: Vector[Vector[Char]]): Set[(Int, Set[(Int, Int)])] =
	val res = scala.collection.mutable.HashSet[(Int, Set[(Int, Int)])]()

	var number = 0
	val numberSet = scala.collection.mutable.HashSet[(Int, Int)]()
	for y <- matrix.indices do
		for x <- matrix(0).indices do
			val c = matrix(y)(x)

			if c.isDigit then
				number = number * 10 + c - '0'
				numberSet.add(x, y) else if number != 0 && !c.isDigit then
				res.add(number, numberSet.toSet)
				number = 0
				numberSet.clear()
	res.toSet

def findSymbols(matrix: Vector[Vector[Char]]): Set[(Int, Int)] =
	val res = scala.collection.mutable.HashSet[(Int, Int)]()

	for y <- matrix.indices do
		for x <- matrix(0).indices do
			val c = matrix(y)(x)

			if !c.isDigit && c != '.' then
				res.add(x, y)

	res.toSet

def adjacentIndices(matrix: Vector[Vector[Char]], pos: (Int, Int)): Set[(Int, Int)] =
	val res = scala.collection.mutable.HashSet[(Int, Int)]()

	val height = matrix.length
	val width = matrix(0).length

	for i <- -1 to 1 do
		for j <- -1 to 1 do
			val x = pos._1 + i
			val y = pos._2 + j
			if 0 <= x && x < width && 0 <= y && y < height then
				res.add(x, y)

	res.toSet

def findPartNumbers(matrix: Vector[Vector[Char]]): Vector[Int] =
	var res = Vector[Int]()

	for symbolLocation <- findSymbols(matrix) do
		for numberLocation <- findNumbers(matrix) do
			if !adjacentIndices(matrix, symbolLocation).intersect(numberLocation._2).isEmpty then
				res :+= numberLocation._1

	res

@main def part1 =
	val matrix = PuzzleInput.parse("day03/part1").map(_.toVector)

	val result = findPartNumbers(matrix)
	println(result.sum)
