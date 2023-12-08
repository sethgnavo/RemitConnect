package com.sethgnavo.remitconnect.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.ui.components.FormattedText
import com.sethgnavo.remitconnect.ui.components.RemitButton
import com.sethgnavo.remitconnect.ui.components.TopNavBar
import com.sethgnavo.remitconnect.ui.navigation.Destinations
import com.sethgnavo.remitconnect.ui.theme.Blue100
import com.sethgnavo.remitconnect.ui.theme.Gray05
import com.sethgnavo.remitconnect.ui.theme.Gray100
import com.sethgnavo.remitconnect.ui.theme.Gray15
import com.sethgnavo.remitconnect.ui.theme.Gray25
import com.sethgnavo.remitconnect.ui.theme.Gray50
import com.sethgnavo.remitconnect.ui.theme.HomeStyle
import com.sethgnavo.remitconnect.ui.theme.Primary05
import com.sethgnavo.remitconnect.ui.theme.Primary100
import com.sethgnavo.remitconnect.ui.theme.Primary15
import com.sethgnavo.remitconnect.ui.theme.Primary70
import com.sethgnavo.remitconnect.ui.theme.RemitConnectTheme
import com.sethgnavo.remitconnect.ui.theme.outfitFontFamily
import com.sethgnavo.remitconnect.utils.formatAmount
import com.sethgnavo.remitconnect.viewmodel.SendMoneyToAfricaFlowViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SendMoneyScreen(
    navController: NavController,
) {
    val activity = LocalContext.current as ComponentActivity
    //val viewModel: SendMoneyToAfricaFlowViewModel = viewModel(viewModelStoreOwner = activity)
    val viewModel: SendMoneyToAfricaFlowViewModel = viewModel(viewModelStoreOwner = activity, factory = SendMoneyToAfricaFlowViewModel.Factory)

    val amountToSendValue by viewModel.amountToSend.observeAsState("")
    val exchangeRate by viewModel.exchangeRateEURXOF.observeAsState(0.0)
    val transferFees by viewModel.transferFees.observeAsState(0.0)
    val monecoFees by viewModel.monecoFees.observeAsState(0.0)
    val totalToSpend by viewModel.totalToSpend.observeAsState(0.0)

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()

    viewModel.getUserBalance()

    RemitConnectTheme {

        Column(Modifier.background(color = Color.White)) {

            TopNavBar(upPress = { navController.popBackStack() })
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {

                Spacer(Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.send_money),
                    style = HomeStyle,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.how_much_are_you_sending),
                    color = Gray100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(8.dp))
                Column(
                    Modifier
                        .padding(horizontal = 24.dp)
                        .border(
                            color = if (amountToSendValue.isBlank()) Gray25 else Primary70,
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp)
                        )

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextField(
                            value = amountToSendValue,
                            placeholder = {
                                Text(
                                    "00", color = Gray100,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            onValueChange = { newText ->
                                if (newText.all { it.isDigit() || it == '.' }) {
                                    viewModel.onAmountToSendChange(newText)
                                }
                            },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            textStyle = TextStyle(
                                color = Gray100,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = outfitFontFamily
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                cursorColor = Primary100,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),

                            )
                        Spacer(Modifier.width(16.dp))
                        Text(
                            text = "EUR",
                            color = Gray50,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.width(16.dp))

                    }
                    Divider(color = if (amountToSendValue.isBlank()) Gray15 else Primary15)
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = if (amountToSendValue.isBlank()) Gray05 else Primary05,
                                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                            )
                            .height(34.dp)
                            .padding(horizontal = 16.dp)

                    ) {

                        FormattedText(
                            sendMoneyViewModel = viewModel,
                            amountToSendValue = amountToSendValue
                        )
                    }
                }
                Spacer(Modifier.height(32.dp))

                Text(
                    text = stringResource(R.string.yearly_free_remittances_litteral),
                    color = Gray100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.remittantce_info),
                    color = Gray50,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.check_remittances_remaining),
                    color = Blue100,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(32.dp))
                Text(
                    text = stringResource(R.string.fees_breakdown),
                    color = Gray100,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(24.dp))

                Row(Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = stringResource(R.string.moneco_fees_litteral),
                        color = Gray50,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${formatAmount(monecoFees)} EUR", color = Gray100
                    )
                }
                Spacer(Modifier.height(16.dp))

                Row(Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = "Transfer fees", color = Gray50, modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${formatAmount(transferFees)} EUR", color = Gray100
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = stringResource(R.string.conversion_rate_litteral),
                        color = Gray50,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${formatAmount(exchangeRate)} XOF", color = Gray100
                    )
                }
                Spacer(Modifier.height(16.dp))

                Row(Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = stringResource(R.string.total_to_spend_litteral),
                        color = Gray50,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${formatAmount(totalToSpend)} EUR",
                        color = Gray100
                    )

                }
                Spacer(Modifier.height(16.dp))

                Canvas(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(1.dp)
                ) {
                    val canvasWidth = size.width
                    drawLine(
                        color = Gray50,
                        start = Offset(0f, 0f),
                        end = Offset(canvasWidth, 0f),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f)),
                        strokeWidth = 2f
                    )
                }
                Spacer(Modifier.height(16.dp))
                Row(Modifier.padding(horizontal = 24.dp)) {
                    Text(
                        text = stringResource(R.string.recipient_gets),
                        color = Gray50,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${
                            if (amountToSendValue.isNotBlank()) {
                                formatAmount(exchangeRate * amountToSendValue.toDouble())
                            } else {
                                "0"
                            }
                        } XOF",
                        color = Gray100,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
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
                    text = stringResource(R.string.send),
                    enabled = amountToSendValue.isNotBlank(),
                    onClick = { showBottomSheet = true },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        if (showBottomSheet) {

            ModalBottomSheet(scrimColor = Color.Black.copy(alpha = 0.4f), dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 24.dp)
                        .height(6.dp)
                        .width(72.dp)
                        .background(color = Gray15, shape = RoundedCornerShape(3.dp))
                )
            }, onDismissRequest = {
                showBottomSheet = false
            }, sheetState = sheetState
            ) {
                Column(Modifier.padding(horizontal = 32.dp)) {
                    Text(
                        stringResource(R.string.confirm_transfer),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(stringResource(R.string.you_are_sending), color = Gray50)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        "${
                            if (amountToSendValue.isNotBlank()) {
                                formatAmount(exchangeRate * amountToSendValue.toDouble())
                            } else {
                                "0"
                            }
                        } XOF",
                        color = Gray100,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(stringResource(R.string.to_litteral), color = Gray50)
                    Text(
                        "${viewModel._selectedRecipient.value?.name}",
                        color = Gray100,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(stringResource(R.string.via_litteral), color = Gray50)
                    Text(
                        "${viewModel._selectedWallet.value?.name} : ${viewModel._selectedRecipient.value?.phoneNumber}",
                        color = Gray100,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.size(32.dp))

                    RemitButton(
                        text = stringResource(R.string.confirm),
                        onClick = {
                            navController.navigate(Destinations.SUCCESS_ROUTE)
                            showBottomSheet = false
                            viewModel.resetSendMoneyToAfricaFlow()
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.size(40.dp))

                }
            }
        }
    }
}