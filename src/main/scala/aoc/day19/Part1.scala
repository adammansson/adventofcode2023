package aoc.day19

import aoc.utils.*
import scala.collection.mutable.ArrayBuffer

case class Rule(rating: Char, pred: Int => Boolean, dest: String)

object Rule:
	def parse(str: String): Option[Rule] =
		str.split(':').toVector match
			case Vector(predStr, destStr) =>
				if predStr.contains('>') then
					Some(Rule(predStr.head, i => i > predStr.drop(2).toInt, destStr))
				else
					Some(Rule(predStr.head, i => i < predStr.drop(2).toInt, destStr))
			case Vector(destStr) =>
				Some(Rule('?', i => true, destStr))
			case _ => None

case class Workflow(name: String, rules: Vector[Rule])

object Workflow:
	def parse(str: String): Option[Workflow] = str.split('{').toVector match
		case Vector(nameStr, rulesStr) =>
			Some(Workflow(nameStr, rulesStr.dropRight(1).split(',').flatMap(Rule.parse).toVector))
		case _ =>
			None

def parsePart(str: String): Option[Map[Char, Int]] = str.drop(1).dropRight(1).split(',').toVector match
	case Vector(xStr, mStr, aStr, sStr) =>
		Some(Map(
			'x' -> xStr.drop(2).toInt,
			'm' -> mStr.drop(2).toInt,
			'a' -> aStr.drop(2).toInt,
			's' -> sStr.drop(2).toInt,
		))
	case _ =>
		None

@main def part1 =
	val input = PuzzleInput(19).toVector.mkString("\n").split("\n\n").toVector
	val workflows = input.head.split("\n").flatMap(Workflow.parse).map(wf => wf.name -> wf).toMap
	val parts = input.last.split("\n").flatMap(parsePart).toVector

	val accepted = ArrayBuffer[Map[Char, Int]]()

	for part <- parts do
		var workflow = workflows("in")

		var running = true
		var ruleCounter = 0
		while running do
			val rule = workflow.rules(ruleCounter)

			if rule.pred(part.getOrElse(rule.rating, -1)) then
				if rule.dest == "A" then
					accepted.addOne(part)
					running = false
				else if rule.dest == "R" then
					running = false
				else
					workflow = workflows(rule.dest)
					ruleCounter = 0
			else
				ruleCounter += 1


	println(accepted.map(_.values.sum).sum)
