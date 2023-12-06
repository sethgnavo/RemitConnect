package com.sethgnavo.remitconnect.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.components.OptionItem
import com.sethgnavo.remitconnect.ui.components.TopNavBar
import com.sethgnavo.remitconnect.ui.navigation.Destinations
import com.sethgnavo.remitconnect.ui.theme.Gray05
import com.sethgnavo.remitconnect.ui.theme.HomeStyle

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SendMoneyOptionsScreen(navController: NavController) {
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxHeight()
    ) {
        TopNavBar(upPress = { navController.popBackStack() })

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.send_money),
            style = HomeStyle,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(24.dp))

        Divider(color = Gray05)
        OptionItem(iconResId = R.drawable.user_square,
            title = stringResource(R.string.to_moneco_balance),
            onClick = {})
        OptionItem(iconResId = R.drawable.store,
            title = stringResource(R.string.bank_transfer),
            onClick = {})
        OptionItem(iconResId = R.drawable.world,
            title = stringResource(R.string.send_to_africa),
            onClick = {
                navController.navigate(Destinations.SendToAfricaRoute)
            })

    }
}


