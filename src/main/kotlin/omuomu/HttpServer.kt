package omuomu

import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.net.InetSocketAddress
import java.util.concurrent.Semaphore
import kotlin.concurrent.thread

const val MAX_AVAILABLE = 10

class HttpServer(val listenPort: Int) {
    val Semaphore = Semaphore(MAX_AVAILABLE)
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
                    val buf = ByteArray(1500)

                    println("Welcome to the Kotlin echo server.")
                    println("AvailablePermits: ${Semaphore.availablePermits()}")

                    while(true) {
                        var len = input.read(buf, 0, buf.size)
                        if(len <= 0) break
                        println("${buf} 0 ${buf.size}")
                        output.write(buf, 0, buf.size)
                    }
                } catch(e: Throwable) {
                    e.printStackTrace(System.err)
                    throw RuntimeException(e)
                } finally {
                    Semaphore.release()
                }
            }
        }
    }
}