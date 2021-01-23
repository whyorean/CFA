package com.cap.front

import android.app.Application
import com.cap.front.data.network.NetworkProvider

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkProvider
            .with(this)
            .bind()
    }
}