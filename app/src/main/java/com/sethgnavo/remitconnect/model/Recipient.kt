package com.sethgnavo.remitconnect.model

data class Recipient(
    var id: String,
    val name: String,
    val country: String,
    val mobileWallet: String,
    var phoneNumber: String?
)