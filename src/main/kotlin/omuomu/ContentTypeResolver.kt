package omuomu

import java.io.IOException

class ContentTypeResolver {

	fun getContentType(fileName: String): String {

        val fileExtention: String = getFileExtension(fileName)

		when (fileExtention) {
            "html", "htm" -> return "text/html"
            "css"         -> return "text/css"
            "js"          -> return "text/javascript"
            "jpeg", "jpg" -> return "image/jpeg"
            "png"         -> return "image/png"
            "txt"         -> return "text/plain"
            else ->{
                return "application/octet-stream"
            }
        }
    }
    // ファイルパスから拡張子を返す
	private fun getFileExtension(fileName: String): String {

        val pathParts: Array<String> = fileName.split("/").toTypedArray()        
		val lastPart: String = pathParts[pathParts.size - 1]

        val pos: Int = lastPart.lastIndexOf(".")
        if(pos == -1) {
            throw IOException("illegal file name")
        } else if (pos > 0 && pos < (lastPart.length - 1)) {
            // "."から後ろの文字列を取り出す
            val extension: String = lastPart.substring(pos + 1)
			return extension.toLowerCase()
		}

		return ""
	}
}