package aoc.day01

import aoc.utils.*
import cats.effect._
import cats.syntax.all._

object Part1 extends IOApp.Simple:
	def run: IO[Unit] =
		for {
			example <- getExample(1, 1)
			_ <- IO.println(sumOfCalibrationValues(example, true))
			input <- getInput(1)
			_ <- IO.println(sumOfCalibrationValues(input, true))
		} yield ()
