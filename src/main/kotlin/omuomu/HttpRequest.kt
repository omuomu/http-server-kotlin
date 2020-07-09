package omuomu

import java.util.List
import omuomu.HttpHeader

public interface HttpRequest {

    enum class HttpMethod {
        GET, PUT, POST, DELETE,
    }

    fun getMethod(): HttpMethod

    fun getPath(): String

    fun getHeaders(): List<HttpHeader>

}