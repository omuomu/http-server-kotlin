package omuomu

import java.io.IOException
import java.io.InputStream

import omuomu.HttpRequest

public interface HttpRequestParser {
    fun parse(input: InputStream): HttpRequest
}
