package aoc.day03

case class Pos(x: Int, y: Int)

def findNumberPositions(matrix: Vector[Vector[Char]]): Vector[(Int, Set[Pos])] =
	var res = scala.collection.mutable.ArrayBuffer[(Int, Set[Pos])]()

	var number = 0
	val numberSet = scala.collection.mutable.HashSet[Pos]()
	for y <- matrix.indices do
		for x <- matrix(0).indices do
			val c = matrix(y)(x)

			if c.isDigit then
				number = number * 10 + c - '0'
				numberSet.add(Pos(x, y))
			else if number != 0 && !c.isDigit then
				res.addOne(number, numberSet.toSet)
				number = 0
				numberSet.clear()
	res.toVector

def findSymbolPositions(matrix: Vector[Vector[Char]], pred: Char => Boolean): Vector[Pos] =
	(for {
		x <- matrix(0).indices
		y <- matrix.indices
		if pred(matrix(y)(x))
	} yield Pos(x, y)).toVector


def adjacentIndices(matrix: Vector[Vector[Char]], pos: Pos): Set[Pos] =
	val res = scala.collection.mutable.HashSet[Pos]()

	val height = matrix.length
	val width = matrix(0).length

	for i <- -1 to 1 do
		for j <- -1 to 1 do
			val x = pos._1 + i
			val y = pos._2 + j
			if 0 <= x && x < width && 0 <= y && y < height then
				res.add(Pos(x, y))

	res.toSet

