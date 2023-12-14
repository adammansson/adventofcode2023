package aoc.day08

import aoc.utils.*

enum Instruction:
	case Left, Right

def parseInstructions(input: String): Vector[Instruction] = input.toUpperCase.map(c => if c == 'L' then Instruction.Left else Instruction.Right).toVector

case class Node(left: String, right: String):
	def next(ins: Instruction): String = ins match
		case Instruction.Left => left
		case _ => right

object Node:
	def apply(str: String): Node =
		val xs = str.filterNot(c => c == '(' || c == ')').split(", ")
		Node(xs(0), xs(1))

def parseGraph(input: Vector[String]): Map[String, Node] =
	input.map(_.split(" = ").toVector).map(xs => xs(0).trim -> Node(xs(1))).toMap

def gcd(a: Long, b: Long): Long = if (b == 0) a.abs else gcd(b, a % b)
def lcm(a: Long, b: Long) = (a * b).abs / gcd(a, b)