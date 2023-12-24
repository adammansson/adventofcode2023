package aoc.utils

import scala.io.Source

import cats.effect._
import cats.effect.syntax.all._
import cats.syntax.all._

import com.comcast.ip4s._
import org.http4s._
import org.http4s.ember.client._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.middleware.Logger
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory
import org.http4s.client.Client
import org.slf4j._
import cats.effect.MonadCancelThrow
import org.typelevel.ci._
import org.http4s.headers.Authorization

private implicit val loggerFactory: LoggerFactory[IO] =
		Slf4jFactory.create[IO]

private def source(name: String): Resource[IO, Source] = Resource.make(IO(Source.fromFile(name)))(source => IO(source.close()))

private def getCookie(): IO[String] = 
	source("session.cookie").use(source => IO(source.mkString))

private def createClient(): Resource[IO, Client[IO]] =
	EmberClientBuilder
		.default[IO]
		.build

private def makeUri(day: Int): Uri =
	uri"https://adventofcode.com".withPath(s"/2023/day/$day/input")

private def getRequest(uri: Uri, cookie: String): Request[IO] =
	Request[IO](
		method = Method.GET,
		uri = uri,
		headers = Headers(
			"Cookie" -> s"session=$cookie",
		)
	)

private def makeRequest(client: Resource[IO, Client[IO]], request: Request[IO]): IO[String] =
	client.use(client => client.expect[String](request))


def getInput(day: Int): IO[IndexedSeq[String]] =
	for {
		cookie <- getCookie()
		response <- makeRequest(createClient(), getRequest(makeUri(day), cookie))

	} yield response.split('\n').toVector
