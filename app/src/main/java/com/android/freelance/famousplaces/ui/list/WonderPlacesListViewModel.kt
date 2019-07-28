package com.android.freelance.famousplaces.ui.list

import androidx.lifecycle.ViewModel
import com.android.freelance.famousplaces.data.Repository.WonderPlacesRepository
import com.android.freelance.famousplaces.internal.lazyDeferred

class WonderPlacesListViewModel(
    private val wonderPlacesRepository: WonderPlacesRepository
) : ViewModel() {

    private val LOG_TAG = WonderPlacesListViewModel::class.java.name

    val wonderPlaces by lazyDeferred{
        wonderPlacesRepository.getWonderPlacesList()
    }

}