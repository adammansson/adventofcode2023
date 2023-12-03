package aoc.day02

def parseSubsets(xs: Vector[String]): Vector[Vector[Vector[(String, Int)]]] =
	xs.map(_.split(": ").last.split("; ").map(_.split(", ").map(s => {val xs = s.split(" "); xs(1) -> xs(0).toInt}).toVector).toVector)