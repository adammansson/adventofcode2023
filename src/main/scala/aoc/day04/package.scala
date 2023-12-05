package aoc.day04

case class Card(winningNumbers: Vector[Int], elfNumbers: Vector[Int]):
	def wins = winningNumbers.intersect(elfNumbers).length
	def points: Int = scala.math.pow(2, wins - 1).floor.toInt

object Card:
	def parseOne(s: String): Option[Card] =
		s.split(":").last.split('|').map(_.split(" ").filterNot(_.isBlank).map(_.toInt).toVector).toVector match
			case Vector(winningNumbers, elfNumbers) => Some(Card(winningNumbers, elfNumbers))
			case _ => None

	def parseMany(input: Vector[String]): Vector[Card] = input.map(parseOne).flatten
