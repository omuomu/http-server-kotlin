package omuomu

import java.io.IOException
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.net.InetSocketAddress
import java.util.concurrent.Semaphore
import omuomu.Http10Processor
import kotlin.concurrent.thread

const val MAX_AVAILABLE = 10

class HttpServer {

    val Semaphore = Semaphore(MAX_AVAILABLE)
    private var documentRoot: File?
    private var listenPort: Int

    constructor(listenPort: Int, documentRoot: File) {
        this.documentRoot = documentRoot
        this.listenPort = listenPort
    }
    public fun start() {
        val sockListen = ServerSocket()
        sockListen.setReuseAddress(true)
        sockListen.bind(InetSocketAddress("0.0.0.0", listenPort))
        while(true) {
            Semaphore.acquire()
            val sockAccept = sockListen.accept()

            thread() {
                try {
                    val input: InputStream = sockAccept.getInputStream()
                    val output: OutputStream = sockAccept.getOutputStream()
                    Http10Processor(this.documentRoot!!).doHttp(input, output)
                } catch(e: Throwable) {
                    e.printStackTrace(System.err)
                    throw RuntimeException(e)
                } catch(e: IOException) {
                    throw IOException(e)
                } finally {
                    Semaphore.release()
                }
            }
        }
    }
}