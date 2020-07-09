package omuomu.impl

import java.io.InputStream

import omuomu.HttpRequest
import omuomu.HttpRequestParser

class HttpRequestParserImpl: HttpRequestParser {
    override fun parse(input: InputStream): HttpRequest {
        return null
    }
}