package com.cap.front.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cap.front.Constants
import com.cap.front.StreamBundle
import com.cap.front.data.network.FuelClient
import com.cap.front.data.network.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class StreamViewModel(application: Application) : BaseAndroidViewModel(application) {


    val liveData: MutableLiveData<StreamBundle> = MutableLiveData()

    init {
        requestState = RequestState.Init
        observe()
    }

    override fun observe() {
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                try {
                    val networkResponse = FuelClient.get(Constants.API_ENDPOINT2)
                    if (networkResponse.isSuccessful) {
                        val weatherData: StreamBundle = gson.fromJson(
                            String(networkResponse.responseBytes),
                            StreamBundle::class.java
                        )
                        liveData.postValue(weatherData)
                        requestState = RequestState.Complete
                    } else {
                        requestState = RequestState.Pending
                    }
                } catch (e: Exception) {
                    requestState = RequestState.Pending
                }
            }
        }
    }
}