package aoc.day10

import aoc.utils.*
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

case class Point(x: Int, y: Int):
	def add(dx: Int, dy: Int): Point =
		Point(x + dx, y + dy)

def findStartingPoint(m: Vector[Vector[Char]]): Point =
	val y = m.indexWhere(_.contains('S'))
	val x = m(y).indexOf('S')
	Point(x, y)

def nextPoints(m: Vector[Vector[Char]], p: Point): Set[Point] =
	m(p.y)(p.x) match
		case '|' => Set(p.add(0, -1), p.add(0, 1))
		case '-' => Set(p.add(-1, 0), p.add(1, 0))
		case 'L' => Set(p.add(0, -1), p.add(1, 0))
		case 'J' => Set(p.add(0, -1), p.add(-1, 0))
		case '7' => Set(p.add(0, 1), p.add(-1, 0))
		case 'F' => Set(p.add(0, 1), p.add(1, 0))
		case _ => Set()

@main def part1 =
	val matrix = PuzzleInput(10).toVector.map(_.toCharArray().toVector)

	val startingPoint = findStartingPoint(matrix)
	var previousPoint = startingPoint
	var currentPoint = startingPoint.add(1, 0)

	val trail = ArrayBuffer[Point](startingPoint, currentPoint)
	while currentPoint != startingPoint do
		val nextPoint = (nextPoints(matrix, currentPoint) - previousPoint).head
		trail += nextPoint
		previousPoint = currentPoint
		currentPoint = nextPoint

	println(trail.length / 2)