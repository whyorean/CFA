package com.cap.front.data.network

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.*
import com.cap.front.util.Log
import java.nio.charset.Charset

object FuelClient : IHttpClient {

    override fun get(url: String, headers: Map<String, String>): NetworkResponse {
        return get(url, headers, hashMapOf())
    }

    override fun get(
        url: String,
        headers: Map<String, String>,
        params: Map<String, String>
    ): NetworkResponse {
        val parameters = params
            .map { it.key to it.value }
            .toList()
        val (request, response, result) = Fuel.get(url, parameters)
            .header(headers)
            .response()
        return buildPlayResponse(response, request)
    }

    override fun get(
        url: String,
        headers: Map<String, String>,
        paramString: String
    ): NetworkResponse {
        val (request, response, result) = Fuel.get(url + paramString)
            .header(headers)
            .response()
        return buildPlayResponse(response, request)
    }

    override fun post(url: String, headers: Map<String, String>, body: ByteArray): NetworkResponse {
        val (request, response, result) = Fuel.post(url)
            .header(headers)
            .appendHeader(Headers.CONTENT_TYPE, "application/json")
            .body(body, Charset.defaultCharset())
            .response()
        return buildPlayResponse(response, request)
    }

    override fun post(
        url: String,
        headers: Map<String, String>,
        params: Map<String, String>
    ): NetworkResponse {
        val parameters = params
            .map { it.key to it.value }
            .toList()
        val (request, response, result) = Fuel.post(url, parameters)
            .header(headers)
            .response()
        return buildPlayResponse(response, request)
    }

    @JvmStatic
    private fun buildPlayResponse(response: Response, request: Request): NetworkResponse {
        return NetworkResponse().apply {
            isSuccessful = response.isSuccessful
            code = response.statusCode

            if (response.isSuccessful) {
                responseBytes = response.body().toByteArray()
            }

            if (response.isClientError || response.isServerError) {
                errorBytes = response.responseMessage.toByteArray()
                errorString = String(errorBytes)
            }
        }.also {
            Log.i("FUEL [${request.method}:${response.statusCode}] ${response.url}")
        }
    }
}