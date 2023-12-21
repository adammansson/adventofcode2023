package aoc.day14

import aoc.utils.*

case class Cycle(start: Int, end: Int)

def rockIndices(xs: Vector[Char]): Vector[Int] =
	xs.zipWithIndex.filter(_._1 == 'O').map(_._2)

def rockMoved(xs: Vector[Char], from: Int, to: Int): Vector[Char] =
	xs.updated(from, '.').updated(to, 'O')

def rocksMovedLeft(xs: Vector[Char]): Vector[Char] =
	rockIndices(xs).foldLeft(xs)((acc, value) => 
		val moveTo = acc.zipWithIndex.take(value).reverse.find(_._1 != '.').map(_._2).getOrElse(-1)
		rockMoved(acc, value, moveTo + 1)
	)

def rocksMovedRight(xs: Vector[Char]): Vector[Char] =
	rockIndices(xs).reverse.foldLeft(xs)((acc, value) => 
		val moveTo = acc.zipWithIndex.drop(value + 1).find(_._1 != '.').map(_._2).getOrElse(xs.length)
		rockMoved(acc, value, moveTo - 1)
	)

def totalLoad(platform: Vector[Vector[Char]]): Int =
	platform.zipWithIndex.map((row, i) => row.count(_ == 'O') * (platform.length - i)).sum

