package omuomu
import omuomu.HttpHeader

public interface HttpRequest {

    enum class HttpMethod {
        GET, PUT, POST, DELETE,
    }

    fun getMethod(): HttpMethod

    fun getPath(): String

    fun getHeaders(): Array<HttpHeader>

    fun getHeader(name: String): HttpHeader?

}