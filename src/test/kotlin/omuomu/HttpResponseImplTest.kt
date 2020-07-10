package omuomu.impl

import java.io.ByteArrayOutputStream

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals
import org.junit.Assert

import omuomu.HttpResponse

public class HttpResponseImplTest {

	@Test
	fun test200() {
		val output: ByteArrayOutputStream = ByteArrayOutputStream()

		val res: HttpResponse = HttpResponseImpl(output)

		res.setStatusCode(200)
		res.addHeader(HttpHeaderImpl("Content-Type", "text/plain"))

		res.getOutputStream().write("hello".toByteArray())

		Assert.assertEquals("HTTP/1.0 200 OK\r\n" + "Content-Type: text/plain\r\n" + "\r\n" + "hello",
				String(output.toByteArray(), Charsets.ISO_8859_1))
	}

	@Test
	fun test404() {
		val output: ByteArrayOutputStream = ByteArrayOutputStream()

		val res: HttpResponse = HttpResponseImpl(output)

		res.setStatusCode(404)
		res.addHeader(HttpHeaderImpl("Content-Type", "text/plain"))

		res.getOutputStream().write("hello".toByteArray())

		Assert.assertEquals("HTTP/1.0 404 Not Found\r\n" + "Content-Type: text/plain\r\n" + "\r\n" + "hello",
				String(output.toByteArray(), Charsets.ISO_8859_1))
	}

	@Test
	fun test500() {
		val output: ByteArrayOutputStream = ByteArrayOutputStream()

		val res: HttpResponse = HttpResponseImpl(output)

		res.setStatusCode(500)
		res.addHeader(HttpHeaderImpl("Content-Type", "text/plain"))

		res.getOutputStream().write("hello".toByteArray())

		Assert.assertEquals(
				"HTTP/1.0 500 Internal Server Error\r\n" + "Content-Type: text/plain\r\n" + "\r\n" + "hello",
				String(output.toByteArray(), Charsets.ISO_8859_1))
	}

}