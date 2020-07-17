package omuomu.impl

import omuomu.HttpRequest
import omuomu.HttpRequest.HttpMethod
import omuomu.HttpHeader

class HttpRequestImpl: HttpRequest {

    private val method: HttpMethod
    private val path: String
    private val headers: Array<HttpHeader>

    constructor(method: HttpMethod, path: String, headers: Array<HttpHeader>) {
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

    override fun getHeaders(): Array<HttpHeader> {
        return this.headers
    }

    override fun getHeader(name: String): HttpHeader? {
        for(header in headers) {
            if(name.equals(header.getName())) {
                return header
            }
        }
        return null
    }
}