package aoc.utils

import scala.io.Source
import cats.effect._
import cats.effect.syntax.all._
import cats.syntax.all._
import org.http4s._
import org.http4s.implicits._
import org.http4s.ember.client._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.client.Client
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory

private implicit val loggerFactory: LoggerFactory[IO] =
		Slf4jFactory.create[IO]

private def getSource(name: String): Resource[IO, Source] =
	Resource.make(IO(Source.fromFile(name)))(source => IO(source.close()))

private def getLines(name: String): IO[IndexedSeq[String]] = 
	getSource(name).use(source => IO(source.getLines().toVector))

private def getClient(): Resource[IO, Client[IO]] =
	EmberClientBuilder
		.default[IO]
		.build

private def getUri(day: Int): Uri =
	uri"https://adventofcode.com".withPath(s"/2023/day/$day/input")

private def getRequest(uri: Uri, cookie: String): Request[IO] =
	Request[IO](
		method = Method.GET,
		uri = uri,
		headers = Headers(
			"Cookie" -> s"session=$cookie",
		)
	)

private def getResponse(client: Resource[IO, Client[IO]], request: Request[IO]): IO[String] =
	client.use(client => client.expect[String](request))

def getExample(day: Int, ordinal: Int): IO[IndexedSeq[String]] =
	getLines(s"input/day${day.toString.padTo(2, '0').reverse}/example$ordinal.txt")

def getInput(day: Int): IO[IndexedSeq[String]] =
	for {
		cookie <- getLines("session.cookie").map(_.head)
		response <- getResponse(getClient(), getRequest(getUri(day), cookie))

	} yield response.split('\n').toVector
