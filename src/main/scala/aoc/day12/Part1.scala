package aoc.day12

import aoc.utils.*

case class ConditionRecord(springs: Vector[Char], groupings: Vector[Int]):
	lazy val possibleArrangement: Boolean = springs.contains('?') match
		case true =>
			false
		case false => 
			val groups = springs.mkString.split('.').filterNot(_.isBlank).map(_.length)
			groups.length == groupings.length && groups.zip(groupings).forall(_ == _)

	def allArrangments: Int = springs.zipWithIndex.find(_._1 == '?').map(_._2) match
		case Some(i) => 
			ConditionRecord(springs.updated(i, '#'), groupings).allArrangments
			+ ConditionRecord(springs.updated(i, '.'), groupings).allArrangments
		case None => 
			if possibleArrangement then
				1
			else
				0

object ConditionRecord:
	def parse(str: String): Option[ConditionRecord] = str.split(' ').toVector match
		case Vector(springsStr, groupingsStr) =>
			Some(ConditionRecord(springsStr.toVector, groupingsStr.split(',').map(_.toInt).toVector))
		case _ =>
			None

@main def part1 =
	val conditionRecords = PuzzleInput(12).toVector.flatMap(ConditionRecord.parse)

	val result = conditionRecords.map(_.allArrangments).sum
	println(result)