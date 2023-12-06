package com.sethgnavo.remitconnect.model

import com.sethgnavo.remitconnect.data.Country
import com.sethgnavo.remitconnect.data.Recipient
import com.sethgnavo.remitconnect.data.Wallet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RemitConnectApiService {
    @GET("wallets")
    suspend fun getWallets(): List<Wallet>

    @GET("countries")
    suspend fun getCountries(): List<Country>

    @GET("recipients")
    suspend fun getRecipients(): List<Recipient>

    companion object {
        var apiService: RemitConnectApiService? = null
        fun getInstance(): RemitConnectApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/MonecoHQ/fake-api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RemitConnectApiService::class.java)
            }
            return apiService!!
        }
    }
}