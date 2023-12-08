package com.sethgnavo.remitconnect.api

import com.sethgnavo.remitconnect.model.Country
import com.sethgnavo.remitconnect.model.Recipient
import com.sethgnavo.remitconnect.model.Wallet
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RemitConnectApiService {
    @GET("wallets")
    suspend fun getWallets(): Response<List<Wallet>>

    @GET("countries")
    suspend fun getCountries(): Response<List<Country>>

    @GET("recipients")
    suspend fun getRecipients(): Response<List<Recipient>>

    companion object {
        var apiService: RemitConnectApiService? = null
        fun getInstance(): RemitConnectApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/MonecoHQ/fake-api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RemitConnectApiService::class.java)
            }
            return apiService!!
        }
    }
}