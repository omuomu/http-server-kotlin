package omuomu.impl

import omuomu.HttpHeader

const val MAX_LENGTH = 8192


public class HttpHeaderImpl() implements HttpHeader {


    val name: String
    val value: String

    constructor(val name: String, val value: String) {
        this.name = name
        this.value = value
    }

    override fun getName() {}

    override fun getValue() {}
}