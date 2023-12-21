package aoc.day16

import scala.collection.mutable.HashSet
import scala.collection.mutable.ArrayBuffer

case class Pos(x: Int, y: Int):
	def +(dir: Dir): Pos = dir match
		case North => Pos(x, y - 1)
		case East => Pos(x + 1, y)
		case South => Pos(x, y + 1)
		case West => Pos(x - 1, y)

enum Dir:
	case North, East, South, West
export Dir.*

class Grid(private val matrix: Vector[Vector[Char]]):
	private val history = Vector.fill(matrix.length)(Vector.fill(matrix(0).length)(HashSet[Dir]()))

	def matrixGet(pos: Pos): Char =
		matrix(pos.y)(pos.x)

	def matrixContains(pos: Pos): Boolean =
		0 <= pos.x && pos.x < matrix(0).length && 0 <= pos.y && pos.y < matrix.length

	def historyAdd(pos: Pos, dir: Dir): Unit =
		history(pos.y)(pos.x).add(dir)
	
	def historyContains(pos: Pos, dir: Dir): Boolean =
		history(pos.y)(pos.x).contains(dir)

	def historySize: Int =
		history.map(xs => xs.count(set => !set.isEmpty)).sum

class Beam(var pos: Pos, var dir: Dir):
	def move(grid: Grid, addBeams: HashSet[Beam], removeBeams: HashSet[Beam]): Unit =
		if !grid.matrixContains(pos) || grid.historyContains(pos, dir) then
			removeBeams.add(this)
		else
			grid.historyAdd(pos, dir)

			dir = grid.matrixGet(pos) match
				case '/' =>
					dir match
						case North => East
						case East => North
						case South => West
						case West => South
				case '\\' =>
					dir match
						case North => West
						case East => South
						case South => East
						case West => North
				case '|' =>
					dir match
						case East =>
							addBeams.add(Beam(pos, South))
							North
						case West =>
							addBeams.add(Beam(pos, South))
							North
						case _ => dir
				case '-' =>
					dir match
						case North => 
							addBeams.add(Beam(pos, West))
							East
						case South =>
							addBeams.add(Beam(pos, West))
							East
						case _ => dir
				case _ => dir
		pos = pos + dir 

def tilesEnergized(matrix: Vector[Vector[Char]], startingBeam: Beam): Int =
	val grid = Grid(matrix)

	val beams = HashSet[Beam](startingBeam)
	val addBeams = HashSet[Beam]()
	val removeBeams = HashSet[Beam]()

	while !beams.isEmpty do
		for beam <- beams do
			beam.move(grid, addBeams, removeBeams)

		beams ++= addBeams
		beams --= removeBeams
		addBeams.clear()
		removeBeams.clear()

	grid.historySize

