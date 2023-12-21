package aoc.day15

import aoc.utils.* 
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable

def focusingPower(boxNbr: Int, slotNbr: Int, focalLength: Int) =
	(boxNbr + 1) * (slotNbr + 1) * focalLength

@main def part2 =
	val initSeq = PuzzleInput(15).toVector.head.split(",").map(parseInitStep).toVector
	val boxes = Array.fill[ArrayBuffer[Lens]](256)(ArrayBuffer())

	initSeq.foreach(_.perform(boxes))

	val result = boxes.zipWithIndex.map((box, boxNbr) => box.zipWithIndex.map((lens, slotNbr) => focusingPower(boxNbr, slotNbr, lens.focalLength)).sum).sum
	println(result)

