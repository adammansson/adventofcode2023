package aoc.day01

private val mappings = IndexedSeq(
	(1, "one"),
	(2, "two"),
	(3, "three"),
	(4, "four"),
	(5, "five"),
	(6, "six"),
	(7, "seven"),
	(8, "eight"),
	(9, "nine"),
	(10, "ten"),
)

private def getFirstDigit(str: String, mappings: IndexedSeq[(Int, String)], ignoreSpelledOut: Boolean): Int =
	mappings.find(mapping => str.startsWith(mapping._1.toString) || (!ignoreSpelledOut && str.startsWith(mapping._2))) match
		case Some(i, _) => i
		case None => getFirstDigit(str.tail, mappings, ignoreSpelledOut)

private def getLastDigit(str: String, mappings: IndexedSeq[(Int, String)], ignoreSpelledOut: Boolean): Int =
	getFirstDigit(str.reverse, mappings.map((i, str) => (i, str.reverse)), ignoreSpelledOut)

private def calibrationValue(line: String, ignoreSpelledOut: Boolean): Int =
	getFirstDigit(line, mappings, ignoreSpelledOut) * 10 + getLastDigit(line, mappings, ignoreSpelledOut)

def sumOfCalibrationValues(calibrationDocument: IndexedSeq[String], ignoreSpelledOut: Boolean): Int =
	calibrationDocument.map(line => calibrationValue(line, ignoreSpelledOut)).sum
