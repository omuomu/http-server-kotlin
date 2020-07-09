package omuomu

import java.io.OutputStream

public interface HttpResponse {

	fun setStatusCode(statusCode: Int)

	fun addHeader(header: HttpHeader)

	fun getOutputStream(): OutputStream
}