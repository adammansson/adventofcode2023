package aoc.day20

import aoc.utils.*

def getModuleConfig(input: Vector[String]): Vector[Module] =
	val moduleConfigWithoutInputs = input.flatMap(Module.parse)

	moduleConfigWithoutInputs.map(_ match
		case c: Conjunction =>
			val inputs = moduleConfigWithoutInputs.filter(_.outputs.contains(c.name)).map(_.name).toVector
			Conjunction(c.name, c.outputs, inputs)
		case other =>
			other
	)

enum Pulse:
	case Low, High
export Pulse.*

case class PulseEntry(pulse: Pulse, from: String, to: String):
	override def toString(): String =
		s"$from -${pulse.toString.toLowerCase}-> $to"

sealed trait Module:
	def name: String
	def outputs: Vector[String]
	def process(pulse: Pulse, from: String): Vector[PulseEntry]

case class FlipFlop(val name: String, val outputs: Vector[String]) extends Module:
	private var isOff = true

	override def process(pulse: Pulse, from: String): Vector[PulseEntry] = pulse match
		case Low =>
			isOff = !isOff
			outputs.map(out => PulseEntry(if isOff then Low else High, name, out))
		case High =>
			Vector()

case class Conjunction(val name: String, val outputs: Vector[String], val inputs: Vector[String]) extends Module:
	private var memory = inputs.map(_ -> Low).toMap

	override def process(pulse: Pulse, from: String): Vector[PulseEntry] =
		memory += from -> pulse
		outputs.map(out => PulseEntry(if memory.values.forall(_ == High) then Low else High, name, out))

case class Broadcaster(name: String, outputs: Vector[String]) extends Module:
	override def process(pulse: Pulse, from: String): Vector[PulseEntry] =
		outputs.map(out => PulseEntry(pulse, name, out))

case class Untyped(name: String) extends Module:
	def outputs: Vector[String] =
		Vector()
	override def process(pulse: Pulse, from: String): Vector[PulseEntry] =
		Vector()

object Module:
	def parse(str: String): Option[Module] = str.split(" -> ").toVector match
		case Vector(nameStr, outputsStr) =>
			val outputs = outputsStr.split(", ").toVector

			if nameStr == "broadcaster" then
				Some(Broadcaster(nameStr, outputs))
			else if nameStr.head == '%' then
				Some(FlipFlop(nameStr.tail, outputs))
			else if nameStr.head == '&' then
				Some(Conjunction(nameStr.tail, outputs, Vector()))
			else
				None
		case _ =>
			None

@main def part1 =
	val moduleConfig = getModuleConfig(PuzzleInput(20).toVector)

	var lowPulses = 0
	var highPulses = 0

	for _ <- 0 until 1_000 do
		var queue = Vector(PulseEntry(Low, "button", "broadcaster"))

		while !queue.isEmpty do
			val entry = queue.head
			queue = queue.tail ++ moduleConfig.find(_.name == entry.to).getOrElse(Untyped(entry.to)).process(entry.pulse, entry.from)

			if entry.pulse == Low then
				lowPulses += 1
			else
				highPulses += 1

	val result = lowPulses * highPulses
	println(result)

/*
@main def part2 =
	val moduleConfig = getModuleConfig(PuzzleInput(20).toVector)

	var found = false
	var pressCounter = 0

	while !found do
		var queue = Vector(PulseEntry(Low, "button", "broadcaster"))
		pressCounter += 1

		while !queue.isEmpty do
			val entry = queue.head
			queue = queue.tail ++ moduleConfig.find(_.name == entry.to).getOrElse(Untyped(entry.to)).process(entry.pulse, entry.from)

			if entry.to == "rx" && entry.pulse == Low then
				found = true

		if pressCounter % 1_000_000 == 0 then
			println(pressCounter)
				
	println(s"Pulse found after $pressCounter presses")
*/