package omuomu

import java.io.File
import omuomu.HttpServer
fun main(args: Array<String>) {
    val listenPort:Int = 12345
    val filePath: File = File("/Users/pc1010/tmp/www")

    HttpServer(listenPort, filePath).start()
}