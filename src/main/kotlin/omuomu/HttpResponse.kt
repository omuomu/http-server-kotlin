package omuomu

import java.io.Closeable
import java.io.OutputStream

public interface HttpResponse : Closeable {

	fun setStatusCode(statusCode: Int)

	fun addHeader(header: HttpHeader)

	fun getOutputStream(): OutputStream
}