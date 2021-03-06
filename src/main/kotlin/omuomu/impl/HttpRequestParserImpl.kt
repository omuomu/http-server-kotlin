package omuomu.impl

import java.io.InputStream
import java.io.IOException
import java.io.BufferedReader
import omuomu.HttpHeader
import omuomu.HttpRequest
import omuomu.HttpRequestParser
import omuomu.HttpRequest.HttpMethod

const val MAX_REQUEST = 128

class HttpRequestParserImpl: HttpRequestParser {
    override fun parse(input: InputStream): HttpRequest {
        val reader: BufferedReader = input.bufferedReader(Charsets.ISO_8859_1)
        val requestLine: String? = reader.readLine()

        if (requestLine == null) {
            throw IOException("failed to read request-line")
        }
        val parts:Array<String> = requestLine.split(" ").toTypedArray()
 
        if (parts.size != 3) {
            throw IOException("illegal request-line")
        }

        val method: HttpMethod = HttpMethod.valueOf(parts[0])
        val path: String = parts[1]
        val httpVersion: String = parts[2]

        
        var headers: Array<HttpHeader> = arrayOf<HttpHeader>()

        while(true) {
            var line: String? = reader.readLine()
            if(line.isNullOrBlank()) {
                break
            }

            var pos: Int = line.indexOf(":")
            if(pos == -1 || pos >= (line.length - 1)) {
                throw IOException("illegal header line")
            }

            val name: String = line.substring(0, pos)
            val value: String = line.substring(pos + 1).trim()

            headers += HttpHeaderImpl(name, value)
        }
        return HttpRequestImpl(method, path, headers)
    }
}