package aoc.day05

case class MapEntry(destinationRangeStart: Long, sourceRangeStart: Long, rangeLength: Long):
	def contains(i: Long): Boolean = 
		sourceRangeStart <= i && i <= sourceRangeStart + rangeLength
	
	def map(i: Long): Long =
		destinationRangeStart + i - sourceRangeStart

case class Seed(start: Long, length: Long)

case class Almanac(seeds: Vector[Seed], maps: Vector[Vector[MapEntry]]):
	private def convert(mapIndex: Int, i: Long): Long =
		maps(mapIndex).find(_.contains(i)).map(_.map(i)).getOrElse(i)

	def minimumLocation: Long =
		var minimumFound = Long.MaxValue

		for {
			seed <- seeds
			x <- seed.start until seed.start + seed.length
		} do
			var source = x
			var destination = convert(0, x)

			for i <- maps.indices.tail do
				source = destination
				destination = convert(i, source)

			if destination < minimumFound then minimumFound = destination
		minimumFound

object Almanac:
	def parse(input: String, isSeedRanges: Boolean): Almanac =
		val seedsLine = input.split("\n").head.split(": ").last.split(" ").map(_.toLong).toVector
		val seeds =
			if isSeedRanges then
				seedsLine.sliding(2, 2).map(xs => Seed(xs(0), xs(1))).toVector
			else
				seedsLine.map(x => Seed(x, 1)).toVector

		val categories = input.split("\n\n").toVector.map(_.split("\n").toVector).tail
		val maps = categories.map(_.tail.map(_.split(" ").map(_.toLong).toVector).map(xs => MapEntry(xs(0), xs(1), xs(2))).sortBy(_.sourceRangeStart))

		Almanac(seeds, maps)
