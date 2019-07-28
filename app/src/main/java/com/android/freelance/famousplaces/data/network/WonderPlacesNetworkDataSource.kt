package com.android.freelance.famousplaces.data.network

import androidx.lifecycle.LiveData
import com.android.freelance.famousplaces.data.network.response.WondersResponse

interface WonderPlacesNetworkDataSource {

    val downloadedWonderPlaces: LiveData<WondersResponse>

    suspend fun fetchWonderPlaces()
}