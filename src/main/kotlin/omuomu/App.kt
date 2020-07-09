package omuomu

import java.io.File
import omuomu.HttpServer
fun main(args: Array<String>) {
    HttpServer(12345, File("/User/pc1010/tmp/www")).start()
}