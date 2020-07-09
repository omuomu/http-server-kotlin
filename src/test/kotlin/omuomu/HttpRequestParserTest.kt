package omuomu

import java.io.ByteArrayInputStream

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals
import omuomu.impl.HttpRequestParserImpl
import omuomu.HttpRequest.HttpMethod
import omuomu.HttpRequestParserFactory


class HttpRequestParseTest {
    val  CRLF: String = "\r\n"
	val  CR: String = "\r"
	val  LF: String = "\n"

    @Test
    fun testNormalCase() {
        val parser: HttpRequestParser = HttpRequestParserFactory().getParser()

        val http: String = makeTestRequest(CRLF)

        val input: ByteArrayInputStream = ByteArrayInputStream(http.toByteArray(Charsets.ISO_8859_1))
        val request: HttpRequest = parser.parse(input)

        assertEquals(request.getMethod(), HttpMethod.GET)
        assertEquals(request.getPath(), "./index.html")

        val headers: Array<HttpHeader> = request.getHeaders()
        assertEquals(headers.size, 3)

        val hostHeader: HttpHeader = headers.get(0)
        assertEquals(hostHeader.getName(), "Host")
		assertEquals(hostHeader.getValue(), "www.dreamarts.co.jp")

        val userAgentHeader = headers.get(1)
        assertEquals(userAgentHeader.getName(), "User-Agent")
        assertEquals(userAgentHeader.getValue(), "Mozilla/5.0")
        
        val connectionHeader = headers.get(2)
        assertEquals(connectionHeader.getName(), "Connection")
        assertEquals(connectionHeader.getValue(), "keep-alive")
    }
    @Test
    fun testLineFeed() {
        val parser: HttpRequestParser = HttpRequestParserFactory().getParser()

        val http: String = makeTestRequest(LF)

        val input: ByteArrayInputStream = ByteArrayInputStream(http.toByteArray(Charsets.ISO_8859_1))
        val request: HttpRequest = parser.parse(input)

        assertEquals(request.getMethod(), HttpMethod.GET)
        assertEquals(request.getPath(), "./index.html")

        val headers: Array<HttpHeader> = request.getHeaders()
        assertEquals(headers.size, 3)

        val hostHeader: HttpHeader = headers.get(0)
        assertEquals(hostHeader.getName(), "Host")
		assertEquals(hostHeader.getValue(), "www.dreamarts.co.jp")

        val userAgentHeader = headers.get(1)
        assertEquals(userAgentHeader.getName(), "User-Agent")
        assertEquals(userAgentHeader.getValue(), "Mozilla/5.0")
        
        val connectionHeader = headers.get(2)
        assertEquals(connectionHeader.getName(), "Connection")
        assertEquals(connectionHeader.getValue(), "keep-alive")
    }
    private fun makeTestRequest(lf: String): String {
        return StringBuilder().append("GET /index.html HTTP/1.1" + lf).append("Host: www.dreamarts.co.jp" + lf)
        .append("User-Agent: Mozilla/5.0" + lf).append("Connection: keep-alive" + lf).append(lf).toString()
    }
}