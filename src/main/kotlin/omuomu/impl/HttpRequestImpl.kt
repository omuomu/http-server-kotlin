package omuomu.impl

import omuomu.HttpRequest
import omuomu.HttpRequest.HttpMethod
import omuomu.HttpHeader

class HttpRequestImpl(method: HttpMethod, path: String, headers: List<HttpHeader>): HttpRequest {

    private val method: HttpMethod
    private val path: String
    private val headers: List<HttpHeader>

    init {
        this.method = method
        this.path = path
        this.headers = headers
    }

    override fun getMethod(): HttpMethod {
        return this.method
    }

    override fun getPath(): String {
        return this.path
    }

    override fun getHeaders(): List<HttpHeader> {
        return this.headers
    }

}