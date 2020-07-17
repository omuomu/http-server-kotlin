package omuomu.impl

import java.io.ByteArrayInputStream

import java.io.Closeable
import java.io.IOException
import java.io.OutputStream
import java.io.UnsupportedEncodingException
import java.util.ArrayList

import omuomu.HttpHeader
import omuomu.HttpResponse

class HttpResponseImpl(output: OutputStream): HttpResponse, Closeable {

	private var output: OutputStream

	private var statusCode: Int = 200

	private var  headers: Array<HttpHeader>  = arrayOf<HttpHeader>()

	private var headerSent: Boolean = false
	init {
		this.output = output
    }

    override fun setStatusCode(statusCode: Int) {
		this.statusCode = statusCode
	}

    override fun addHeader(header: HttpHeader) {
		this.headers += header
	}

    override fun getOutputStream(): OutputStream {
		output.write(buildResponseHeader())
		headerSent = true
		return output
	}

	override fun close() {
		if(!headerSent) {
			output.write(buildResponseHeader())
		}
	}

	private fun buildResponseHeader():ByteArray {
		try {
			val http: StringBuilder = StringBuilder()

			http.append("HTTP/1.0 ")
				.append(statusCode)
				.append(" ")
				.append(getStatusText())
				.append("\r\n")

			for (header in headers) {
				http.append(header.getName())
					.append(": ")
					.append(header.getValue())
					.append("\r\n")
			}

			http.append("\r\n")
			return http.toString().toByteArray(Charsets.ISO_8859_1)
		} catch (e: UnsupportedEncodingException) {
			throw RuntimeException(e)
		}
	}

	private fun getStatusText():String {
        when (statusCode) {
            200 -> return "OK"
            404 -> return "Not Found"
            500 -> return "Internal Server Error"
            else ->	return "Not Implemented"
        }
	}
}