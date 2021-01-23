package com.cap.front.data.network

class NetworkResponse {
    var responseBytes: ByteArray = byteArrayOf()
    var errorBytes: ByteArray = byteArrayOf()
    var errorString: String = ("Api Error")
    var isSuccessful: Boolean = false
    var code: Int = 400
}