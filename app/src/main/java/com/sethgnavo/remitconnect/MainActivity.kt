package com.sethgnavo.remitconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sethgnavo.remitconnect.ui.theme.RemitConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemitConnectTheme {
                App()
            }
        }
    }
}
