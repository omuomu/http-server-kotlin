package omuomu

import omuomu.HttpRequestParser
import omuomu.impl.HttpRequestParserImpl

class HttpRequestParserFactory {

    fun getParser(): HttpRequestParser {
        return HttpRequestParserImpl()
    }
}