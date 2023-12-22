package aoc.day20

import aoc.utils.*
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue

enum Pulse:
	case Low, High
export Pulse.*

case class PulseEntry(pulse: Pulse, from: String, to: String):
	override def toString(): String =
		s"$from -${if pulse == High then "high" else "low"}-> $to"

sealed trait Module:
	def name: String
	def outputs: Vector[String]
	def process(pulse: Pulse, from: String, queue: Queue[PulseEntry]): Unit

case class FlipFlop(val name: String, val outputs: Vector[String]) extends Module:
	private var isOff = true

	def process(pulse: Pulse, from: String, queue: Queue[PulseEntry]): Unit = pulse match
		case Low =>
			outputs.foreach(out => queue.enqueue(PulseEntry(if isOff then High else Low, name, out)))
			isOff = !isOff
		case High =>
			{}

case class Conjunction(val name: String, val outputs: Vector[String]) extends Module:
	private val memory = HashMap[String, Pulse]()

	def addInputs(inputs: Vector[String]): Unit =
		inputs.foreach(in => memory += in -> Low)

	def process(pulse: Pulse, from: String, queue: Queue[PulseEntry]): Unit =
		memory += from -> pulse
		outputs.foreach(out => queue.enqueue(PulseEntry(if memory.values.forall(_ == High) then Low else High, name, out)))

case class Broadcaster(name: String, outputs: Vector[String]) extends Module:
	def process(pulse: Pulse, from: String, queue: Queue[PulseEntry]): Unit =
		outputs.foreach(out => queue.enqueue(PulseEntry(pulse, name, out)))

case class Untyped(name: String) extends Module:
	def outputs: Vector[String] =
		Vector()
	def process(pulse: Pulse, from: String, queue: Queue[PulseEntry]): Unit =
		{}

object Module:
	def parse(str: String): Option[Module] = str.split(" -> ").toVector match
		case Vector(nameStr, outputsStr) =>
			val outputs = outputsStr.split(", ").toVector

			if nameStr == "broadcaster" then
				Some(Broadcaster(nameStr, outputs))
			else if nameStr.head == '%' then
				Some(FlipFlop(nameStr.tail, outputs))
			else if nameStr.head == '&' then
				Some(Conjunction(nameStr.tail, outputs))
			else
				None
		case _ =>
			None

@main def part1 =
	val moduleConfig = PuzzleInput(20).toVector.flatMap(Module.parse)
	moduleConfig.foreach(_ match
		case c: Conjunction =>
			c.addInputs(moduleConfig.filter(_.outputs.contains(c.name)).map(_.name).toVector)
		case _ =>
			{}
	)

	var lowPulses = 0
	var highPulses = 0

	val queue = Queue[PulseEntry]()
	val history = ArrayBuffer[PulseEntry]()

	for _ <- 0 until 1_000 do
		queue.enqueue(PulseEntry(Low, "button", "broadcaster"))

		while !queue.isEmpty do
			val entry = queue.dequeue
			history += entry
			moduleConfig.find(_.name == entry.to).getOrElse(Untyped(entry.to)).process(entry.pulse, entry.from, queue)

		lowPulses += history.count(_.pulse == Low)
		highPulses += history.count(_.pulse == High)

		queue.clear()
		history.clear()

	val result = lowPulses * highPulses
	println(result)