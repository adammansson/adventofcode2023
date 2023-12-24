package aoc.day01

import aoc.utils.*
import cats.effect._
import cats.syntax.all._

object Part1 extends IOApp.Simple:
	def run: IO[Unit] =
		for {
			input <- getInput(1)
			_ <- IO.println(input.map(sumFirstLastDigit(_)).sum)
		} yield ()