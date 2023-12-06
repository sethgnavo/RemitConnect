package com.sethgnavo.remitconnect.data

data class Recipient(
    var id: String,
    val name: String,
    val country: String,
    val mobileWallet: String,
    var phoneNumber: String?
)