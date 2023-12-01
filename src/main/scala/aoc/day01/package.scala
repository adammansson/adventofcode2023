package aoc.day01

def sumFirstLastDigit(s: String): Int =
	val digits = s.filter(_.isDigit)
	(digits.take(1) ++ digits.reverse.take(1)).toInt