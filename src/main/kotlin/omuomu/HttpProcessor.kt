package omuomu

import java.io.InputStream
import java.io.OutputStream

interface HttpProcessor {
	fun doHttp(input: InputStream, output: OutputStream)
}