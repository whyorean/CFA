package com.cap.front.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cap.front.data.network.NetworkProvider
import com.cap.front.data.network.RequestState
import com.cap.front.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Modifier

abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application),
    NetworkProvider.NetworkListener {

    private lateinit var networkListener: NetworkProvider.NetworkListener

    protected val gson: Gson = GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
        .create()

    protected var requestState: RequestState

    init {
        Log.i("${javaClass.simpleName} Created")

        requestState = RequestState.Init

        NetworkProvider.addListener(this)
    }

    abstract fun observe()

    override fun onConnected() {

    }

    override fun onDisconnected() {

    }

    override fun onReconnected() {
        redoLastNetworkTask()
    }

    private fun redoLastNetworkTask() {
        when (requestState) {
            RequestState.Pending, RequestState.Init -> {
                observe()
            }
            else -> {
                Log.i("${javaClass.simpleName} Network task already complete")
            }
        }
    }

    override fun onCleared() {
        Log.i("${javaClass.simpleName} Destroyed")
        NetworkProvider.removeListener(this)
        super.onCleared()
    }
}