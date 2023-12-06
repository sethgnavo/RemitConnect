package com.sethgnavo.remitconnect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.theme.Accent
import com.sethgnavo.remitconnect.ui.theme.Gray05
import com.sethgnavo.remitconnect.ui.theme.Primary100

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    optionsRight: List<Pair<String, Int>>,
    optionsLeft: List<Pair<String, Int>>,
    onSendMoneyClick: () -> Unit = {}
) {
    Column {
        Divider(color = Gray05)
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {

                optionsRight.forEach { option ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = option.second),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                        )
                        Text(option.first, fontSize = 12.sp)
                    }
                }

            }
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .shadow(
                        elevation = 16.dp,
                        ambientColor = Primary100,
                        spotColor = Color.Black.copy(0.5f), shape = CircleShape
                    )
                    .background(color = Accent)
                    .clickable(onClick = onSendMoneyClick)


            ) {
                Image(
                    painter = painterResource(id = R.drawable.paper_plane),
                    contentDescription = stringResource(id = R.string.send_money),
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {

                optionsLeft.forEach { option ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = option.second),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(option.first, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}