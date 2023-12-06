package com.sethgnavo.remitconnect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.theme.Gray05
import com.sethgnavo.remitconnect.ui.theme.Gray100
import com.sethgnavo.remitconnect.ui.theme.Primary05

@Composable
fun OptionItem(iconResId: Int, title: String, onClick: () -> Unit) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .clickable(onClick = onClick)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(width = 40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .height(height = 40.dp)
                    .background(color = Primary05),
            ) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                title,
                color = Gray100,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.chevron_right), contentDescription = null
            )
        }
        Divider(color = Gray05)
    }
}
