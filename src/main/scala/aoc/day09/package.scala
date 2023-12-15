package aoc.day09

def parseReport(input: Vector[String]): Vector[Vector[Int]] =
	input.map(line => line.split(" ").map(_.toInt).toVector)

def levels(xs: Vector[Int]): Vector[Vector[Int]] =
	val diff = xs.sliding(2).map(xs => (xs(1), xs(0))).map(_ - _).toVector
	
	if diff.forall(_ == 0) then
		Vector(xs)
	else
		levels(diff) :+ xs

def solve(input: Vector[String], extrapolation: Vector[Vector[Int]] => Int): Int =
	parseReport(input).map(history => extrapolation(levels(history))).sum
