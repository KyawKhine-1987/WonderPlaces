package com.android.freelance.famousplaces.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.freelance.famousplaces.data.network.response.WondersResponse
import com.android.freelance.famousplaces.internal.NoConnectivityException

class WonderPlacesNetworkDataSourceImpl(
    private val wonderPlacesApiService: WonderPlacesApiService
) : WonderPlacesNetworkDataSource {

    private val LOG_TAG = WonderPlacesNetworkDataSourceImpl::class.java.name

    private val _downloadedWonderPlaces = MutableLiveData<WondersResponse>() //It is used to Encapsulation Method.
    override val downloadedWonderPlaces: LiveData<WondersResponse>
        get() = _downloadedWonderPlaces

    override suspend fun fetchWonderPlaces() {
        Log.i(LOG_TAG, "TEST: fetchWonderPlaces() called...")

        try {
            val fetchedWonderPlaces = wonderPlacesApiService.getWondersPlaces().await()
            _downloadedWonderPlaces.postValue(fetchedWonderPlaces)
        } catch (e: NoConnectivityException) {
            Log.e(LOG_TAG + "Connectivity", "No Internet Connection!", e)
        }
    }
}