package com.sethgnavo.remitconnect.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.data.Wallet
import com.sethgnavo.remitconnect.ui.components.RemitButton
import com.sethgnavo.remitconnect.ui.components.TopNavBar
import com.sethgnavo.remitconnect.ui.navigation.Destinations
import com.sethgnavo.remitconnect.ui.theme.Gray100
import com.sethgnavo.remitconnect.ui.theme.Gray15
import com.sethgnavo.remitconnect.ui.theme.HomeStyle
import com.sethgnavo.remitconnect.ui.theme.Primary100
import com.sethgnavo.remitconnect.viewmodel.SendMoneyToAfricaFlowViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MobileWalletsScreen(
    navController: NavController
) {
    val activity = LocalContext.current as ComponentActivity
    val viewModel: SendMoneyToAfricaFlowViewModel = viewModel(viewModelStoreOwner = activity)

    val wallets = viewModel._walletListResponse
    viewModel.getWalletList()

    var selectedWallet by remember { mutableStateOf<Wallet?>(null) }

    Column(Modifier.background(color = Color.White)) {
        TopNavBar(upPress = { navController.popBackStack() })

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.choose_mobile_wallet),
            style = HomeStyle,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(Modifier.height(24.dp))

        Column(Modifier.weight(1f)) {
            wallets.forEach { item ->
                val strokeColor = if (item == selectedWallet) Primary100 else Gray15
                val backgroundColor =
                    if (item == selectedWallet) Primary100.copy(alpha = 0.1f) else Color.White

                Box(modifier = Modifier

                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .clickable(onClick = {
                        selectedWallet = item
                        viewModel._selectedWallet.value = item

                    },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() })
                    .border(
                        border = BorderStroke(width = 1.dp, color = strokeColor),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
                    .fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_wave_logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = Color.Transparent, shape = RoundedCornerShape(12.dp)
                                )
                        )
                        Spacer(Modifier.size(16.dp))
                        Text(
                            text = item.name,
                            fontSize = 16.sp,
                            color = Gray100,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )

                    }
                }
            }
        }

        Box(
            Modifier
                .shadow(elevation = 24.dp)
                .background(color = Color.White)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            RemitButton(
                text = stringResource(id = R.string.continue_litteral),
                enabled = selectedWallet != null,
                onClick = { navController.navigate(Destinations.SendMoneyRoute) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
