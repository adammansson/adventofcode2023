package aoc.day01

import aoc.utils.*
import cats.effect._
import cats.syntax.all._

object Part2 extends IOApp.Simple:
	def run: IO[Unit] =
		for {
			example <- getExample(1, 2)
			_ <- IO.println(sumOfCalibrationValues(example, false))
			input <- getInput(1)
			_ <- IO.println(sumOfCalibrationValues(input, false))
		} yield ()
