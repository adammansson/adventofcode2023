package aoc.day15

import aoc.utils.* 
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable

case class Lens(label: String, focalLength: Int)

sealed trait InitStep:
	def perform(boxes: Array[ArrayBuffer[Lens]]): Unit

case class LensAdd(lens: Lens) extends InitStep:
	def perform(boxes: Array[ArrayBuffer[Lens]]): Unit =
		val box = hash(lens.label)
		val lensIdx = boxes(box).indexWhere(_.label == lens.label)
		if lensIdx == -1 then
			boxes(box).addOne(lens)
		else
			boxes(box)(lensIdx) = lens

case class LensRemove(label: String) extends InitStep:
	def perform(boxes: Array[ArrayBuffer[Lens]]): Unit =
		val box = hash(label)
		boxes(box).filterInPlace(_.label != label)

def parseInitStep(str: String): InitStep =
	if str.last == '-' then
		LensRemove(str.dropRight(1))
	else
		val xs = str.split("=")
		LensAdd(Lens(xs.head, xs.last.toInt))

def focusingPower(boxNbr: Int, slotNbr: Int, focalLength: Int) =
	(boxNbr + 1) * (slotNbr + 1) * focalLength

@main def part2 =
	val initSeq = PuzzleInput(15).toVector.head.split(",").map(parseInitStep).toVector
	val boxes = Array.fill[ArrayBuffer[Lens]](256)(ArrayBuffer())

	initSeq.foreach(_.perform(boxes))

	val result = boxes.zipWithIndex.map((box, boxNbr) => box.zipWithIndex.map((lens, slotNbr) => focusingPower(boxNbr, slotNbr, lens.focalLength)).sum).sum
	println(result)

