package aoc.day13

import aoc.utils.*

def isSymmetric(m: Vector[Vector[Char]], mid: Int): Boolean =
	val distToEdge = math.min(mid, m.length - mid)

	var i = 0
	var isSymmetric = true
	while i < distToEdge && isSymmetric do
		if m(mid - i - 1) != m(mid + i) then
			isSymmetric = false
		else
			i += 1

	isSymmetric

def findReflection(m: Vector[Vector[Char]]): Option[Int] =
	m.indices.drop(1).map(i => if isSymmetric(m, i) then Some(i) else None).flatten.headOption

@main def part1 =
	val patterns = PuzzleInput(13).toVector.mkString("\n").split("\n\n").toVector.map(_.split("\n").map(_.toVector).toVector)
	val result = 
		patterns.map(pattern => findReflection(pattern) match
			case Some(i) => i * 100
			case None => findReflection(pattern.transpose).get
		).sum
		
	println(result)