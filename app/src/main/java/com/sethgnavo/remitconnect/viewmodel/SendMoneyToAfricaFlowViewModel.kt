package com.sethgnavo.remitconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sethgnavo.remitconnect.data.Recipient
import com.sethgnavo.remitconnect.data.Wallet
import com.sethgnavo.remitconnect.model.RemitConnectApiService
import kotlinx.coroutines.launch

class SendMoneyToAfricaFlowViewModel : ViewModel() {

    val _selectedRecipient = MutableLiveData<Recipient?>()
    val _selectedWallet = MutableLiveData<Wallet?>()

    var _walletListResponse: List<Wallet> by mutableStateOf(listOf())
    var _recipientListResponse: List<Recipient> by mutableStateOf(listOf())
    var _filteredRecipientListResponse: List<Recipient> by mutableStateOf(listOf())
    var _recipientsListErrorMessage: String by mutableStateOf("")
    var _walletsListErrorMessage: String by mutableStateOf("")

    private val _recipientPhoneNumber = MutableLiveData("")
    private val _recipientFirstName = MutableLiveData("")
    private val _recipientLastName = MutableLiveData("")
    val recipientPhoneNumber: LiveData<String> = _recipientPhoneNumber
    val recipientFirstName: LiveData<String> = _recipientFirstName
    val recipientLastName: LiveData<String> = _recipientLastName

    private val _userBalance = MutableLiveData<Double>()
    val userBalance: LiveData<Double> = _userBalance

    private val _monecoFees = MutableLiveData<Double>(0.0)
    val monecoFees: LiveData<Double> = _monecoFees

    private val _transferFees = MutableLiveData<Double>()
    val transferFees: LiveData<Double> = _transferFees

    private val _exchangeRateEURXOF = MutableLiveData<Double>(655.97)
    val exchangeRateEURXOF: LiveData<Double> = _exchangeRateEURXOF

    private val _amountToSend = MutableLiveData<String>()
    val amountToSend: LiveData<String> = _amountToSend

    private val _searchRecipientQuery = MutableLiveData<String>("")
    val searchRecipientQuery: LiveData<String> = _searchRecipientQuery

    // LiveData that calculates to total amount to spend (monecoFees+amountToSend)
    val totalToSpend: LiveData<Double> = MediatorLiveData<Double>().apply {
        var currentMonecoFees = 0.0
        var currentAmountToSend = 0.0

        fun updateTotal() {
            val amount = currentAmountToSend ?: 0.0
            value = currentMonecoFees + amount
        }

        addSource(_monecoFees) { fees ->
            currentMonecoFees = fees ?: 0.0
            updateTotal()
        }

        addSource(_amountToSend) { amount ->
            currentAmountToSend = amount.toDoubleOrNull() ?: 0.0
            updateTotal()
        }
    }

    fun getWalletList() {
        viewModelScope.launch {
            val apiService = RemitConnectApiService.getInstance()

            try {
                val walletList = apiService.getWallets()
                _walletListResponse = walletList
            } catch (e: Exception) {
                _walletsListErrorMessage = e.message.toString()
            }
        }
    }

    fun onSearchRecipientQueryChange(newValue: String) {
        _searchRecipientQuery.value = newValue
    }

    fun onRecipientPhoneNumberChange(newValue: String) {
        _recipientPhoneNumber.value = newValue
    }

    fun onRecipientFirstNameChange(newValue: String) {
        _recipientFirstName.value = newValue
    }

    fun onRecipientLastNameChange(newValue: String) {
        _recipientLastName.value = newValue
    }

    init {
        searchRecipientQuery.observeForever {
            filterRecipients()
        }
    }

    private fun filterRecipients() {
        val query = searchRecipientQuery.value ?: ""
        _filteredRecipientListResponse = if (query.isEmpty()) {
            // If the search query is empty, return the entire list
            _recipientListResponse
        } else {
            _recipientListResponse.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }

    fun getRecipientList() {
        viewModelScope.launch {
            val apiService = RemitConnectApiService.getInstance()

            try {
                val recipientList = apiService.getRecipients()
                _recipientListResponse = recipientList
                filterRecipients() // Re-filter the list whenever it's updated
            } catch (e: Exception) {
                _recipientsListErrorMessage = e.message.toString()
            }
        }
    }

    fun onAmountToSendChange(newValue: String) {
        _amountToSend.value = newValue
    }

    fun getUserBalance() {
        _userBalance.value = 320000.0//TODO Fetch actual balance from the backend/local storage
    }

    fun resetSendMoneyToAfricaFlow() {
        _selectedRecipient.value = null
        _selectedWallet.value = null
        _recipientPhoneNumber.value = ""
        _recipientFirstName.value = ""
        _recipientLastName.value = ""
        _amountToSend.value = ""
    }

}