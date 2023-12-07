package aoc.day07

import aoc.utils.*

enum CardWithJoker:
	case Joker, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Queen, King, Ace

enum HandType:
	case HighCard, OnePair, TwoPair, ThreeOfAKind, FullHouse, FourOfAKind, FiveOfAKind

object CardWithJoker:
	def fromChar(c: Char): CardWithJoker = c match
		case '2' => Two
		case '3' => Three
		case '4' => Four
		case '5' => Five
		case '6' => Six
		case '7' => Seven
		case '8' => Eight
		case '9' => Nine
		case 'T' => Ten
		case 'J' => Joker
		case 'Q' => Queen
		case 'K' => King
		case 'A' => Ace

case class HandWithJoker(cards: Vector[CardWithJoker], bid: Int) extends Ordered[HandWithJoker]:
	val strength: HandType = 
		import HandType.*

		val tally = Array.fill(13)(0)

		for card <- cards do
			tally(card.ordinal) += 1

		val numberOfJokers = tally(CardWithJoker.Joker.ordinal)
		tally(CardWithJoker.Joker.ordinal) = 0

		if numberOfJokers == 5 then FiveOfAKind
		else if numberOfJokers == 4 then FiveOfAKind
		else if numberOfJokers == 3 then
			if tally.contains(2) then FiveOfAKind
			else FourOfAKind
		else if numberOfJokers == 2 then
			if tally.contains(3) then FiveOfAKind
			else if tally.contains(2) then FourOfAKind
			else ThreeOfAKind
		else if numberOfJokers == 1 then
			if tally.contains(4) then FiveOfAKind
			else if tally.contains(3) then FourOfAKind
			else if tally.count(_ == 2) == 2 then FullHouse
			else if tally.contains(2) then ThreeOfAKind
			else OnePair
		else
			if tally.contains(5) then FiveOfAKind
			else if tally.contains(4) then FourOfAKind
			else if tally.contains(3) && tally.contains(2) then FullHouse
			else if tally.contains(3) then ThreeOfAKind
			else if tally.count(_ == 2) == 2 then TwoPair
			else if tally.contains(2) then OnePair
			else HighCard
		
	override def compare(that: HandWithJoker): Int =
		if strength == that.strength then
			val i = cards.indices.find(index => cards(index) != that.cards(index)).getOrElse(5)
			cards(i).ordinal - that.cards(i).ordinal
		else
			strength.ordinal - that.strength.ordinal

@main def part2 =
	val input = PuzzleInput(7).toVector.map(_.split(" ")).map(xs => HandWithJoker(xs(0).map(CardWithJoker.fromChar(_)).toVector, xs(1).toInt))
	val result = input.sorted.zipWithIndex.map((hand, index) => hand.bid * (index + 1)).sum
	println(result)