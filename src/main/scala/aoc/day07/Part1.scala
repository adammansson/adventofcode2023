package aoc.day07

import aoc.utils.*

enum Card:
	case Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace

object Card:
	def fromChar(c: Char): Card = c match
		case '2' => Two
		case '3' => Three
		case '4' => Four
		case '5' => Five
		case '6' => Six
		case '7' => Seven
		case '8' => Eight
		case '9' => Nine
		case 'T' => Ten
		case 'J' => Jack
		case 'Q' => Queen
		case 'K' => King
		case 'A' => Ace

case class Hand(cards: Vector[Card], bid: Int) extends Ordered[Hand]:
	val strength: Int = 
		val tally = Array.fill(13)(0)

		for card <- cards do
			tally(card.ordinal) += 1

		if tally.contains(5) then 6
		else if tally.contains(4) then 5
		else if tally.contains(3) && tally.contains(2) then 4
		else if tally.contains(3) then 3
		else if tally.count(_ == 2) == 2 then 2
		else if tally.contains(2) then 1
		else 0
		
	override def compare(that: Hand): Int =
		if strength == that.strength then
			val i = cards.indices.find(index => cards(index) != that.cards(index)).getOrElse(5)
			cards(i).ordinal - that.cards(i).ordinal
		else
			strength - that.strength

@main def part1 =
	println(PuzzleInput(7).toVector.map(_.split(" ")).map(xs => Hand(xs(0).map(Card.fromChar(_)).toVector, xs(1).toInt)).sorted.zipWithIndex.map((hand, index) => hand.bid * (index + 1)).sum)