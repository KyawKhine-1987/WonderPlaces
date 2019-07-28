package com.android.freelance.famousplaces.data.network

import com.android.freelance.famousplaces.data.network.response.ConnectivityInterceptor
import com.android.freelance.famousplaces.data.network.response.WondersResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//https://api.myjson.com/bins/13g69v

interface WonderPlacesApiService {

    @GET("bins/13g69v")
    fun getWondersPlaces(
    ): Deferred<WondersResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor : ConnectivityInterceptor
        ): WonderPlacesApiService {
            val requestInterceptor = Interceptor{chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor) // this is control the unstable connection which is used to ConnectivityInterceptor Interface then called the ConnectivityInterceptorImpl class.
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.myjson.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WonderPlacesApiService::class.java)
        }
    }
}