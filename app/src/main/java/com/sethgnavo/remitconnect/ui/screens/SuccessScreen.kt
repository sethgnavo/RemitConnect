package com.sethgnavo.remitconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.components.RemitButton
import com.sethgnavo.remitconnect.ui.navigation.Destinations
import com.sethgnavo.remitconnect.ui.theme.Primary70

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessScreen(
    navController: NavController,
) {

    Column(
        modifier = Modifier
            .background(color = Primary70)
            .padding(horizontal = 32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.success),
            contentDescription = null,
            modifier = Modifier.size(204.dp)
        )
        Spacer(modifier = Modifier.size(24.dp))

        Text(
            stringResource(R.string.money_sent_successful_litteral),
            color = Color.White,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(56.dp))

        RemitButton(
            text = stringResource(R.string.got_it),
            onClick = {
                navController.popBackStack(Destinations.HomeRoute, inclusive = true)
                navController.navigate(Destinations.HomeRoute)
            },
        )
    }
}
