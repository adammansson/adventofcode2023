package aoc.day18

import aoc.utils.*
import scala.collection.mutable.HashSet
import scala.collection.mutable.Queue

def excavateTrenches(digPlan: Vector[DigInstruction]): Set[Pos] =
	var pos = Pos(0, 0)
	val xs = HashSet[Pos](pos)

	for ins <- digPlan do
		for _ <- 0 until ins.steps do
			pos = pos + ins.dir
			xs.addOne(pos)

	xs.map(p => Pos(p.x + xs.minBy(_.x).x.abs, p.y + xs.minBy(_.y).y.abs)).toSet

def generateTerrain(trenches: Set[Pos]): Array[Array[Boolean]] =
	(0 to trenches.maxBy(_.y).y).map(y => (0 to trenches.maxBy(_.x).x).map(x => trenches.contains(Pos(x, y))).toArray).toArray

def printTerrain(terrain: Array[Array[Boolean]]): Unit =
	println(terrain.map(_.map(if _ then '#' else '.').mkString).mkString("\n") + "\n")

def excavateLagoon(terrain: Array[Array[Boolean]], lagoonPos: Pos): Unit =
	val edges = Queue(lagoonPos)

	while !edges.isEmpty do
		val p = edges.dequeue()

		if !terrain(p.y)(p.x) then
			terrain(p.y)(p.x) = true

			edges.enqueue(p + Up)
			edges.enqueue(p + Down)
			edges.enqueue(p + Left)
			edges.enqueue(p + Right)

@main def part1 =
	val digPlan = PuzzleInput(18).toVector.flatMap(DigInstruction.parse)

	val trenches = excavateTrenches(digPlan)
	val terrain = generateTerrain(trenches)
	val lagoonPos = Pos(terrain(1).indexWhere(identity) + 1, 1)
	excavateLagoon(terrain, lagoonPos)

	val result = terrain.flatten.count(identity)
	println(result)