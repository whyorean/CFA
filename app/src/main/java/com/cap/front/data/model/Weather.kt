package com.cap.front


class Weather {
    var temp: Int = 0
    var time: Long = 0L
    var rain: Int = 0
    var wind: Int = 0

    override fun hashCode(): Int {
        return temp
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Weather -> other.time == time
            else -> false
        }
    }
}

data class WeatherData(val data: List<Weather>)