package aoc.day15

def hash(str: String): Int =
	str.foldLeft(0)((acc, value) => ((acc + value.toInt) * 17) % 256)