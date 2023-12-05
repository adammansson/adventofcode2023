package aoc.day05

case class Mapping(dst: Long, src: Long, len: Long)

def parseMap(lines: Vector[String]): Vector[Mapping] =
	val res = scala.collection.mutable.ArrayBuffer[Mapping]()

	for line <- lines.tail.map(_.split(" ").map(_.toLong).toVector) do
		val dst = line(0)
		val src = line(1)
		val len = line(2)

		res.addOne(Mapping(dst, src, len))

	res.toVector

def applyMapping(xs: Vector[Mapping], i: Long): Long =
	var res = i
	var j = 0
	while res == i && j < xs.length do
		val m = xs(j)
		if m.src <= i && i <= m.src + m.len then
			res = m.dst + (i - m.src)
		j += 1

	res