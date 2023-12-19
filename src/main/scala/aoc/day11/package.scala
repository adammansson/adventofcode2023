package aoc.day11

import aoc.utils.*

case class Galaxy(x: Int, y: Int)

def findEmpty(xss: Vector[Vector[Char]]): Vector[Int] =
	xss.zipWithIndex.filterNot(_._1.contains('#')).map(_._2)

def findGalaxies(xss: Vector[Vector[Char]]): Vector[Galaxy] =
	xss.zipWithIndex.flatMap((row, y) => row.zipWithIndex.flatMap((c, x) => if c == '#' then Some(Galaxy(x, y)) else None))

def expandAmount(i: Int, xs: Vector[Int], expandBy: Int) =
	xs.count(_ < i) * (expandBy - 1)

def expandGalaxies(galaxies: Vector[Galaxy], emptyRows: Vector[Int], emptyCols: Vector[Int], expandBy: Int): Vector[Galaxy] =
	galaxies.map(p => Galaxy(p.x + expandAmount(p.x, emptyCols, expandBy), p.y + expandAmount(p.y, emptyRows, expandBy)))

def generatePairs(galaxies: Vector[Galaxy]): Vector[(Galaxy, Galaxy)] =
	(for {
		g0 <- galaxies
		g1 <- galaxies
	} yield if g0 != g1 then Some(g0, g1) else None).flatten.toVector

def distanceBetween(g0: Galaxy, g1: Galaxy): Long =
	(g0.x - g1.x).abs + (g0.y - g1.y).abs

def solve(expandBy: Int): Long =
	val rows = PuzzleInput(11).toVector.map(_.toCharArray.toVector)
	val (emptyRows, emptyCols) = (findEmpty(rows), findEmpty(rows.transpose))
	val galaxies = findGalaxies(rows)
	val expandedGalaxies = expandGalaxies(galaxies, emptyRows, emptyCols, expandBy)

	val pairs = generatePairs(expandedGalaxies)
	pairs.map(distanceBetween).sum / 2