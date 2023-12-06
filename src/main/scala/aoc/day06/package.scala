package aoc.day06

case class Race(timeAllowed: Long, recordDistance: Long):
	def waysToBeatRecord: Long =
		var res = 0L

		for {
			timeToHold <- 0L to timeAllowed
			if (timeToHold * (timeAllowed - timeToHold) > recordDistance)
		} res += 1
			
		res
