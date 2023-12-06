package com.sethgnavo.remitconnect.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.theme.Gray100
import com.sethgnavo.remitconnect.ui.theme.Primary70
import com.sethgnavo.remitconnect.utils.formatAmount
import com.sethgnavo.remitconnect.viewmodel.SendMoneyToAfricaFlowViewModel

@Composable
fun FormattedText(sendMoneyViewModel: SendMoneyToAfricaFlowViewModel, amountToSendValue: String) {
    LaunchedEffect(key1 = Unit) {
        sendMoneyViewModel.getUserBalance()
    }
    val balance = sendMoneyViewModel.userBalance.observeAsState(0.0)
    val text = buildAnnotatedString {
        append(stringResource(R.string.current_balance_notice))

        // Apply bold style to the amount
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append(formatAmount(balance.value))
            append(" EUR")
        }
    }
    Text(
        text = text,
        color = if (amountToSendValue.isBlank()) Gray100 else Primary70,
        fontSize = 12.sp
    )
}