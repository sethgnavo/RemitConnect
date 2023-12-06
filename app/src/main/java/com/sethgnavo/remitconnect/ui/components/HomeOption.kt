package com.sethgnavo.remitconnect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sethgnavo.remitconnect.ui.theme.Primary05
import com.sethgnavo.remitconnect.ui.theme.Primary100
import com.sethgnavo.remitconnect.ui.theme.ShadowColor

@Composable
fun HomeOption(iconResId: Int, title: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
            .shadow(
                elevation = 20.dp,
                ambientColor = Primary100,
                spotColor = ShadowColor,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White
            )
            .clickable { }
            .padding(all = 16.dp)

    ) {
        Column {
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, color = Primary100, fontWeight = FontWeight.Medium)
        }
    }
}
