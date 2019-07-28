package com.android.freelance.famousplaces.data.Repository

import androidx.lifecycle.LiveData
import com.android.freelance.famousplaces.data.db.entity.WonderPlacesEntry
import com.android.freelance.famousplaces.data.db.entity.Wonders

interface WonderPlacesRepository {

     suspend fun getWonderPlacesList() : LiveData<List<WonderPlacesEntry>> // confused.
}