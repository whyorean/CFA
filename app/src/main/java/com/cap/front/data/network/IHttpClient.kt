package com.cap.front.data.network

import java.io.IOException

interface IHttpClient {
    @Throws(IOException::class)
    fun post(url: String, headers: Map<String, String> = mapOf(), body: ByteArray): NetworkResponse

    @Throws(IOException::class)
    fun post(
        url: String,
        headers: Map<String, String> = mapOf(),
        params: Map<String, String> = mapOf()
    ): NetworkResponse

    @Throws(IOException::class)
    fun get(url: String, headers: Map<String, String> = mapOf()): NetworkResponse

    @Throws(IOException::class)
    fun get(
        url: String,
        headers: Map<String, String> = mapOf(),
        params: Map<String, String> = mapOf()
    ): NetworkResponse

    @Throws(IOException::class)
    fun get(url: String, headers: Map<String, String> = mapOf(), paramString: String): NetworkResponse
}