package com.android.freelance.famousplaces.data.network

import com.android.freelance.famousplaces.data.network.response.WondersResponse
import io.reactivex.Observable
import retrofit2.http.GET


//https://api.myjson.com/bins/13g69v

interface ApiWonders {

    @GET("/bins/13g69v")
    fun getWonders(): Observable<WondersResponse>

}