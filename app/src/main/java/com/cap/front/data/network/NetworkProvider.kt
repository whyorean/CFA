package com.cap.front.data.network

import android.content.Context
import com.cap.front.SingletonHolder
import com.cap.front.util.Log
import com.novoda.merlin.Merlin

class NetworkProvider(var context: Context) {

    companion object : SingletonHolder<NetworkProvider, Context>(::NetworkProvider) {

        private var networkListeners: MutableList<NetworkListener> = mutableListOf()

        fun addListener(networkListener: NetworkListener) {
            Log.i("Merlin Added to ${networkListener.javaClass.simpleName}")
            networkListeners.add(networkListener)
        }

        fun removeListener(networkListener: NetworkListener) {
            Log.i("Merlin Removed from ${networkListener.javaClass.simpleName}")
            networkListeners.remove(networkListener)
        }
    }

    private var merlin: Merlin = Merlin.Builder()
        .withAllCallbacks()
        .build(context)

    private var isDisconnected = true

    fun bind() {
        merlin.bind()

        merlin.registerConnectable {
            if (isDisconnected) {
                isDisconnected = false
                onReConnected()
            } else {
                onConnected()
            }
        }

        merlin.registerDisconnectable {
            isDisconnected = true
            onDisconnected()
        }
    }

    fun unbind() {
        networkListeners.clear()
        merlin.unbind()
        Log.i("Merlin Destroyed")
    }

    private fun onConnected() {
        Log.i("Merlin Connected")
        isDisconnected = false
        networkListeners.forEach {
            it.onConnected()
        }
    }

    private fun onReConnected() {
        Log.i("Merlin Re-Connected")
        networkListeners.forEach {
            it.onReconnected()
        }
    }

    private fun onDisconnected() {
        Log.e("Merlin Disconnected")
        networkListeners.forEach {
            it.onDisconnected()
        }
    }

    interface NetworkListener {
        fun onConnected()
        fun onDisconnected()
        fun onReconnected()
    }
}