package aoc.day14

import aoc.utils.*
import scala.collection.mutable.ArrayBuffer

def afterSpin(platform: Vector[Vector[Char]]): Vector[Vector[Char]] =
	platform.transpose.map(rocksMovedLeft).transpose.map(rocksMovedLeft).transpose.map(rocksMovedRight).transpose.map(rocksMovedRight)

def findCycle(platform: Vector[Vector[Char]]): (Cycle, Vector[Vector[Char]]) =
	var tempPlatform = platform
	val cachedPlatforms = ArrayBuffer(platform.flatten.mkString)
	var cycle: Option[Cycle] = None

	var counter = 0	
	while cycle.isEmpty do
		tempPlatform = afterSpin(tempPlatform)

		val cachedIndex = cachedPlatforms.indexOf(tempPlatform.flatten.mkString)
		if cachedIndex != -1 then
			cycle = Some(Cycle(cachedIndex, counter))
		else
			cachedPlatforms.addOne(tempPlatform.flatten.mkString)

		counter += 1

	(cycle.get, tempPlatform)

def remainingCycles(cycle: Cycle): Int =
	(1_000_000_000 - cycle.start) % (cycle.end - cycle.start + 1)

def afterSpins(platform: Vector[Vector[Char]], amount: Int): Vector[Vector[Char]] =
	(0 until amount).foldLeft(platform)((acc, _) => afterSpin(acc))

@main def part2 =
	val originalPlatform = PuzzleInput(14).toVector.map(_.toVector)
	val (cycle, updatedPlatform) = findCycle(originalPlatform)
	val finalPlatform = afterSpins(updatedPlatform, remainingCycles(cycle))

	println(totalLoad(finalPlatform))
