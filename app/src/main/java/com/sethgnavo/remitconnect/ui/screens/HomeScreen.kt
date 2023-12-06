package com.sethgnavo.remitconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.components.BottomNavBar
import com.sethgnavo.remitconnect.ui.components.HomeOption
import com.sethgnavo.remitconnect.ui.components.TransactionItem
import com.sethgnavo.remitconnect.ui.components.TransactionType
import com.sethgnavo.remitconnect.ui.navigation.Destinations
import com.sethgnavo.remitconnect.ui.theme.GeneralBackground
import com.sethgnavo.remitconnect.ui.theme.GradientEnd
import com.sethgnavo.remitconnect.ui.theme.GradientStart
import com.sethgnavo.remitconnect.ui.theme.Gray05
import com.sethgnavo.remitconnect.ui.theme.Gray100
import com.sethgnavo.remitconnect.ui.theme.Gray50
import com.sethgnavo.remitconnect.ui.theme.HomeStyle
import com.sethgnavo.remitconnect.ui.theme.Primary100
import com.sethgnavo.remitconnect.ui.theme.ShadowColor
import com.sethgnavo.remitconnect.ui.theme.outfitFontFamily
import com.sethgnavo.remitconnect.utils.formatAmount
import com.sethgnavo.remitconnect.viewmodel.SendMoneyToAfricaFlowViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(
    navController: NavController, sendMoneyViewModel: SendMoneyToAfricaFlowViewModel? = viewModel()
) {
    LaunchedEffect(key1 = Unit) {
        sendMoneyViewModel?.getUserBalance()
    }
    val balance = sendMoneyViewModel?.userBalance?.observeAsState(0.0)

    Surface(color = GeneralBackground) {

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.height(height = 56.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Hey, John Doe",
                        style = HomeStyle,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    IconButton(
                        onClick = {}, modifier = Modifier
                            .statusBarsPadding()
                            .background(
                                color = Gray05, shape = RoundedCornerShape(10.dp)
                            )
                            .size(40.dp)

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bell_f),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(height = 24.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .height(height = 160.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    GradientStart, GradientEnd
                                )
                            )
                        )
                        .padding(all = 24.dp),
                ) {
                    Row {
                        Column {
                            Text(
                                text = stringResource(R.string.your_balance),
                                color = Color.White,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = formatAmount(balance?.value),
                                style = HomeStyle,
                                color = Color.White,
                                modifier = Modifier
                            )
                            Text(text = "Euros", color = Color.White, fontSize = 12.sp)

                        }
                        Spacer(modifier = Modifier.weight(1f))

                        Box(
                            modifier = Modifier
                                .width(width = 56.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .height(height = 56.dp)
                                .background(color = Color.White.copy(alpha = 0.2F)),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.moneys),
                                contentDescription = null,
                                modifier = Modifier.align(alignment = Alignment.Center)
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(height = 24.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(Modifier.weight(1f)) {
                        HomeOption(
                            iconResId = R.drawable.empty_wallet_add,
                            title = stringResource(R.string.top_up_balance)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(Modifier.weight(1f)) {
                        HomeOption(
                            iconResId = R.drawable.wallet_minus,
                            title = stringResource(R.string.withdraw_money)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(Modifier.weight(1f)) {
                        HomeOption(
                            iconResId = R.drawable.card,
                            title = stringResource(R.string.get_iban_litteral)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(Modifier.weight(1f)) {
                        HomeOption(
                            iconResId = R.drawable.percentage_square,
                            title = stringResource(R.string.view_analytics)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    stringResource(R.string.transactions),
                    color = Gray100,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    Modifier.shadow(
                        elevation = 20.dp,
                        ambientColor = Primary100,
                        spotColor = ShadowColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                ) {
                    Column {
                        TransactionItem(
                            transactionType = TransactionType.Sent,
                            recipent = "Ralph Edwards",
                            amount = 150.0
                        )
                        TransactionItem(
                            transactionType = TransactionType.Sent,
                            recipent = "Eleanor Pena",
                            amount = 150.0
                        )
                        TransactionItem(
                            transactionType = TransactionType.Received,
                            recipent = "Leslie Alexander",
                            amount = 150.0
                        )
                        TransactionItem(
                            transactionType = TransactionType.Spent,
                            recipent = "Burger King",
                            amount = 150.0
                        )
                        TextButton(
                            onClick = { }, modifier = Modifier
                                .background(
                                    Color.White, shape = RoundedCornerShape(
                                        bottomStart = 16.dp, bottomEnd = 16.dp
                                    )
                                )
                                .fillMaxWidth()
                                .height(40.dp), shape = RoundedCornerShape(
                                bottomStart = 16.dp, bottomEnd = 16.dp
                            ), colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White, contentColor = Gray50
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.show_all_activity),
                                fontFamily = outfitFontFamily
                            )
                        }
                        Spacer(Modifier.size(16.dp))
                    }
                }
            }
            BottomNavBar(optionsRight = listOf(
                Pair(stringResource(R.string.nav_home), R.drawable.home_f),
                Pair(stringResource(R.string.nav_cards), R.drawable.credit_card_f)
            ),
                optionsLeft = listOf(
                    Pair(stringResource(R.string.nav_tontines), R.drawable.coin_f),
                    Pair(stringResource(R.string.nav_settings), R.drawable.cog_f)
                ),
                modifier = Modifier.fillMaxWidth(),
                onSendMoneyClick = { navController.navigate(Destinations.SendMoneyOptionsRoute) })
        }
    }
}

