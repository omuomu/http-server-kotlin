package omuomu.impl

import omuomu.HttpHeader

const val MAX_LENGTH = 8192


public class HttpHeaderImpl: HttpHeader {


    private val name: String
    private val value: String

    constructor(name: String, value: String) {
        if (name.toByteArray().size > MAX_LENGTH) {
            throw IllegalArgumentException()
        }
        if (value.toByteArray().size > MAX_LENGTH) {
            throw IllegalArgumentException()
        }
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