package omuomu.impl

import java.io.InputStream
import java.io.IOException

import omuomu.HttpHeader
import omuomu.HttpRequest
import omuomu.HttpRequestParser
import omuomu.HttpRequest.HttpMethod

const val MAX_REQUEST = 128

class HttpRequestParserImpl: HttpRequestParser {
    override fun parse(input: InputStream): HttpRequest {
        val requestLine: String? = input.bufferedReader(Charsets.ISO_8859_1).use { it.readText() }
        val tempLine: String? = requestLine
        println(requestLine)

        if (requestLine == null) {
            throw IOException("failed to read request-line")
        }
        val parts = requestLine.split(" ").toTypedArray()
        if (parts.size != 3) {
            throw IOException("illegal request-line")
        }

        val method: HttpMethod = HttpMethod.valueOf(parts[0])
        val path: String = parts[1]
        val httpVersion: String = parts[2]

        var headers: Array<HttpHeader> = arrayOf<HttpHeader>()
        val line: String? = null
        while (tempLine != null) {
            if (tempLine.isBlank()) {
                break
            }

            val pos: Int = tempLine.indexOf(":")
            // 存在しない場合 pos = -1
            // |0|1|2|3|4|5|
            // | | | | | |:|
            //            ^- length - 1
            if(pos == -1 || pos >= (tempLine.length - 1)) {
                throw IOException("illegal header line")
            }
            val name: String = line!!.substring(0, pos)
            val value: String = line.substring(pos + 1).trim()

            headers += HttpHeaderImpl(name, value)
        }
        return HttpRequestImpl(method, path, headers)
    }
}