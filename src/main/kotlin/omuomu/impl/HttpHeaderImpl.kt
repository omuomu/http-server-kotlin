package omuomu.impl

import omuomu.HttpHeader

const val MAX_LENGTH = 8192


public class HttpHeaderImpl(name: String, value: String): HttpHeader {


    private val name: String
    private val value: String

    init {
        this.name = name
        this.value = value
    }

    override fun getName(): String {
        return this.name
    }

    override fun getValue(): String {
        return this.value
    }
}