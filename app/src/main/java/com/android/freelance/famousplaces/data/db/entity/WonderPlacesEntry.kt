package com.android.freelance.famousplaces.data.db.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class WonderPlacesEntry (
    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "image_url")
    val image: String,

    @ColumnInfo(name = "lat")
    val lat: Double,

    @ColumnInfo(name = "long")
    val long: Double
)