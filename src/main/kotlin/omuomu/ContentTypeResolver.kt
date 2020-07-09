package omuomu


class ContentTypeResolver {

	fun getContentType(fileName: String): String {

        val ext: String = getFileExtension(fileName)

		when (ext) {
            "html", "htm" -> return "text/html"
            "css" -> return "text/css"
            "js"  -> return "text/javascript"
            "jpeg", "jpg" -> return "image/jpeg"
            "png" -> return "image/png"
            "txt" -> return "text/plain"
            else ->{
                return "application/octet-stream"
            }
        }
    }

	private fun getFileExtension(fileName: String): String {

        val pathParts: Array<String> = fileName.split("/").toTypedArray()        
		val lastPart: String = pathParts[pathParts.size - 1]

		val pos: Int = lastPart.lastIndexOf(".")
		if (pos > 0 && pos < (lastPart.length - 1)) {
			return lastPart.substring(pos + 1).toLowerCase()
		}

		return ""
	}
}