//> using lib "com.softwaremill.sttp.client4::core:4.0.0-M8"

package aoc.utils

case class PuzzleInput(day: Int):
	import scala.io.Source
	import sttp.client4.quick.*
	import sttp.client4.Response

	private val responseBody = quickRequest
		.cookie("session", Source.fromFile("session.cookie").mkString)
		.get(uri"https://adventofcode.com/2023/day/$day/input")
		.send()
		.body

	def toVector: Vector[String] = 
		responseBody.split("\n").toVector