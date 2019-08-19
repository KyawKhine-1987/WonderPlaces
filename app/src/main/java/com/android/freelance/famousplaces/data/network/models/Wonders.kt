package com.android.freelance.famousplaces.data.network.models

import com.google.gson.annotations.SerializedName

data class Wonders(
    @SerializedName("location")
    val location: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("lat")
    val lat: Double,

    @SerializedName("long")
    val long: Double
)