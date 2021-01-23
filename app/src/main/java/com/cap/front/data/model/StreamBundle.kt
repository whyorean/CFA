package com.cap.front

import java.util.*

data class StreamBundle(
    var id:String = UUID.randomUUID().toString(),
    var code: String,
    var content: List<Content>
)
