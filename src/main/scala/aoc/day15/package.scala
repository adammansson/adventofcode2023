package aoc.day15

import scala.collection.mutable.ArrayBuffer

def hash(str: String): Int =
	str.foldLeft(0)((acc, value) => ((acc + value.toInt) * 17) % 256)

case class Lens(label: String, focalLength: Int)

sealed trait InitStep:
	def perform(boxes: Array[ArrayBuffer[Lens]]): Unit
	def hashCode: Int

case class LensAdd(lens: Lens) extends InitStep:
	def perform(boxes: Array[ArrayBuffer[Lens]]): Unit =
		val box = hash(lens.label)
		val lensIdx = boxes(box).indexWhere(_.label == lens.label)
		if lensIdx == -1 then
			boxes(box).addOne(lens)
		else
			boxes(box)(lensIdx) = lens

	override lazy val hashCode: Int = hash(lens.label + "=" + lens.focalLength.toString)

case class LensRemove(label: String) extends InitStep:
	def perform(boxes: Array[ArrayBuffer[Lens]]): Unit =
		val box = hash(label)
		boxes(box).filterInPlace(_.label != label)

	override lazy val hashCode: Int = hash(label + "-")

def parseInitStep(str: String): InitStep =
	if str.last == '-' then
		LensRemove(str.dropRight(1))
	else
		val xs = str.split("=")
		LensAdd(Lens(xs.head, xs.last.toInt))