package com.cap.front.data.network

sealed class RequestState {
    object Init : RequestState()
    object Pending : RequestState()
    object Complete : RequestState()
}