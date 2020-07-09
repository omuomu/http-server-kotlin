package omuomu

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

import omuomu.impl.HttpHeaderImpl
import omuomu.HttpProcessor
import omuomu.HttpRequest
import omuomu.HttpRequestParser
import omuomu.HttpRequestParserFactory
import omuomu.HttpResponse
import omuomu.impl.HttpResponseImpl

class Http10Processor: HttpProcessor {

	override fun doHttp(input: InputStream, output: OutputStream) {
		val parser: HttpRequestParser = HttpRequestParserFactory().getParser()
		val req: HttpRequest = parser.parse(input)

		val res: HttpResponse = HttpResponseImpl(output)

		res.setStatusCode(200)
		res.addHeader(HttpHeaderImpl("Server", "ore-server"))
		res.addHeader(HttpHeaderImpl("Content-Type", "text/plain"))

		res.getOutputStream().write("Hello".toByteArray())
	}

}