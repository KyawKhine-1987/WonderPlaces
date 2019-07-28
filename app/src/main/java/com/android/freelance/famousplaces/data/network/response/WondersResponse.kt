package com.android.freelance.famousplaces.data.network.response

import com.android.freelance.famousplaces.data.db.entity.Wonders
import com.google.gson.annotations.SerializedName


data class WondersResponse(

    @SerializedName("wonders")
    val wonders: List<Wonders>
)