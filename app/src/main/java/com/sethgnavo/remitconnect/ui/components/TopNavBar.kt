package com.sethgnavo.remitconnect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.theme.Gray05

@Composable
fun TopNavBar(
    upPress: () -> Unit = {},
    showCloseButton: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        if (!showCloseButton) {
            IconButton(
                onClick = upPress,
                modifier = Modifier
                    .statusBarsPadding()
                    .background(
                        color = Gray05,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        if (showCloseButton) {
            IconButton(
                onClick = upPress,
                modifier = Modifier
                    .statusBarsPadding()
                    .background(
                        color = Gray05,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

    }
}
