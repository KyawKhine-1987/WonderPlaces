package com.android.freelance.famousplaces.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.freelance.famousplaces.data.Repository.WonderPlacesRepository

class WonderPlacesListViewModelFactory(
    private val wonderPlacesRepository: WonderPlacesRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WonderPlacesListViewModel(wonderPlacesRepository) as T
    }
}