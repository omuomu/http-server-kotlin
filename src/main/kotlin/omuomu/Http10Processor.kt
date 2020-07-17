package omuomu

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

import java.util.Date
import java.io.File
import java.io.FileInputStream
import omuomu.impl.HttpHeaderImpl
import omuomu.HttpProcessor
import omuomu.HttpRequest
import omuomu.HttpRequestParser
import omuomu.HttpRequestParserFactory
import omuomu.HttpResponse
import omuomu.HttpHeader
import omuomu.impl.HttpResponseImpl
import omuomu.ContentTypeResolver

class Http10Processor: HttpProcessor {

	private var documentRoot: File? = null

	constructor(documentRoot: File){
		this.documentRoot = documentRoot
	}
	override fun doHttp(input: InputStream, output: OutputStream) {
		
		var keepAliveCount: Int = 0

		while(true) {
			val parser: HttpRequestParser = HttpRequestParserFactory().getParser()

			val req: HttpRequest = parser.parse(input)
			var keepAlive: Boolean = true

			// connectionHeaderの値がcloseの時切断
			val connectionHeader: HttpHeader? = req.getHeader("Connection")
			if (connectionHeader != null) {
				if(connectionHeader.getValue().equals("close")){
					keepAlive = false
				}
			}

			val res: HttpResponse = HttpResponseImpl(output)

			// パスが存在するか
			val path: File = getRealPath(req)
			if (!path.exists()) {
				res.setStatusCode(404)
				return
			}

			// Content-Typeのレスポンス生成
			val contentType = ContentTypeResolver().getContentType(path.getAbsolutePath())
			res.setStatusCode(200)
			res.addHeader(HttpHeaderImpl("Content-Type", contentType))
			sendFile(res.getOutputStream(), path)

			if(!keepAlive) {
				break
			}

			keepAliveCount ++
		}
	}

	private fun sendFile(out: OutputStream, path: File) {

		val input: FileInputStream = FileInputStream(path)

		while (true) {
			val buf: ByteArray = ByteArray(1500)
			val len: Int = input.read(buf, 0, buf.size)
			if (len < 0) {
				break
			}
			out.write(buf, 0, len)
		}
	}

	private fun getRealPath(req: HttpRequest): File {
		var path: String = req.getPath()

		if (path.endsWith("/")) {
			path += "index.html"
		}

		if (path.startsWith("/")) {
			path = path.substring(1)
		}
		// File("/foo/./bar/gav/../baaz").normalize() is File("/foo/bar/baaz")
		return File(documentRoot, path).normalize()
	}

}