package aoc.day10

import aoc.utils.*
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

enum Direction:
	case North, East, South, West
export Direction.*

case class Point(x: Int, y: Int):
	def +(dir: Direction): Point = dir match
		case North => Point(x, y - 1)
		case East => Point(x + 1, y)
		case South => Point(x, y + 1)
		case West => Point(x - 1, y)

def findStartingPoint(m: Vector[Vector[Char]]): Point =
	val y = m.indexWhere(_.contains('S'))
	val x = m(y).indexOf('S')
	Point(x, y)

def nextPoints(m: Vector[Vector[Char]], p: Point): Set[Point] =
	m(p.y)(p.x) match
		case '|' => Set(p + North, p + South)
		case '-' => Set(p + East, p + West)
		case 'L' => Set(p + North, p + East)
		case 'J' => Set(p + North, p + West)
		case '7' => Set(p + South, p + West)
		case 'F' => Set(p + South, p + East)
		case _ => Set()

@main def part1 =
	val matrix = PuzzleInput(10).toVector.map(_.toCharArray().toVector)
	val startingPoint = findStartingPoint(matrix)

	val trail = ArrayBuffer(startingPoint, startingPoint + East)
	while trail.last != startingPoint do
		trail += (nextPoints(matrix, trail.last) - trail(trail.length - 2)).head

	println(trail.length / 2)