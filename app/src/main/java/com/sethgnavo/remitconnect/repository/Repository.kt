package com.sethgnavo.remitconnect.repository

import android.util.Log
import com.sethgnavo.remitconnect.model.Recipient
import com.sethgnavo.remitconnect.model.Wallet
import com.sethgnavo.remitconnect.api.RemitConnectApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class Repository {

    val apiService = RemitConnectApiService.getInstance()

    fun fetchWallets(): Flow<NetworkResult<List<Wallet>>> = flow {
        try {
            val response = apiService.getWallets()

            if (response.isSuccessful) {
                response.body()?.let { wallets ->
                    emit(NetworkResult.Success(wallets))
                } ?: emit(NetworkResult.Error(NullPointerException("Response body is null")))
            } else {
                emit(NetworkResult.Error(HttpException(response)))
            }

        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
            Log.d("WALLETS: ", "An error occurred: ${e.message}")
        }
    }

    fun fetchRecipients(): Flow<NetworkResult<List<Recipient>>> = flow {
        try {
            val response = apiService.getRecipients()

            if (response.isSuccessful) {
                response.body()?.let { recipients ->
                    emit(NetworkResult.Success(recipients))
                } ?: emit(NetworkResult.Error(NullPointerException("Response body is null")))
            } else {
                emit(NetworkResult.Error(HttpException(response)))
            }

        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
            Log.d("RECIPIENTS: ", "An error occurred: ${e.message}")
        }
    }

}

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
