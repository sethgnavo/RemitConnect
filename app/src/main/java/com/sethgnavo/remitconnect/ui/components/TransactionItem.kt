package com.sethgnavo.remitconnect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.theme.Accent
import com.sethgnavo.remitconnect.ui.theme.Accent10
import com.sethgnavo.remitconnect.ui.theme.Blue10
import com.sethgnavo.remitconnect.ui.theme.Blue100
import com.sethgnavo.remitconnect.ui.theme.Gray05
import com.sethgnavo.remitconnect.ui.theme.Gray100
import com.sethgnavo.remitconnect.ui.theme.Gray50
import com.sethgnavo.remitconnect.ui.theme.Primary05
import com.sethgnavo.remitconnect.ui.theme.Primary100
import com.sethgnavo.remitconnect.utils.formatAmount


enum class TransactionType {
    Sent,
    Received,
    Spent
}

@Composable
fun TransactionItem(transactionType: TransactionType, recipent: String, amount: Double) {
    val desc = when (transactionType) {
        TransactionType.Sent -> stringResource(R.string.sent_to)
        TransactionType.Received -> stringResource(R.string.received_from)
        TransactionType.Spent -> stringResource(R.string.spent_at)
    }
    val backgroundColor = when (transactionType) {
        TransactionType.Sent -> Blue10
        TransactionType.Received -> Primary05
        TransactionType.Spent -> Accent10
    }
    val iconColor = when (transactionType) {
        TransactionType.Sent -> Blue100
        TransactionType.Received -> Primary100
        TransactionType.Spent -> Accent
    }
    val iconRes = when (transactionType) {
        TransactionType.Sent -> R.drawable.arrow_up_right
        TransactionType.Received -> R.drawable.arrow_down_left
        TransactionType.Spent -> R.drawable.credit_card_f
    }

    Column {
        Row(
            Modifier
                .clickable { }
                .background(color = Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(width = 40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .height(height = 40.dp)
                    .background(color = backgroundColor),
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.align(alignment = Alignment.Center),
                    colorFilter = ColorFilter.tint(iconColor)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(desc, color = Gray50, fontSize = 12.sp)
                Text(recipent, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Gray100)

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("€ " + formatAmount(amount), fontWeight = FontWeight.Medium)
        }
        Divider(color = Gray05)

    }
}
