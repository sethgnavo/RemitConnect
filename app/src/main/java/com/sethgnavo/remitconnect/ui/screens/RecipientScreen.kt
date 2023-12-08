package com.sethgnavo.remitconnect.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sethgnavo.remitconnect.R
import com.sethgnavo.remitconnect.model.Recipient
import com.sethgnavo.remitconnect.repository.NetworkResult
import com.sethgnavo.remitconnect.ui.components.ContactItem
import com.sethgnavo.remitconnect.ui.components.RemitButton
import com.sethgnavo.remitconnect.ui.components.RemitIconButton
import com.sethgnavo.remitconnect.ui.components.SegmentedControl
import com.sethgnavo.remitconnect.ui.components.TopNavBar
import com.sethgnavo.remitconnect.ui.navigation.Destinations
import com.sethgnavo.remitconnect.ui.theme.Gray05
import com.sethgnavo.remitconnect.ui.theme.Gray100
import com.sethgnavo.remitconnect.ui.theme.Gray25
import com.sethgnavo.remitconnect.ui.theme.Gray50
import com.sethgnavo.remitconnect.ui.theme.HomeStyle
import com.sethgnavo.remitconnect.ui.theme.Primary05
import com.sethgnavo.remitconnect.ui.theme.Primary100
import com.sethgnavo.remitconnect.ui.theme.Primary15
import com.sethgnavo.remitconnect.viewmodel.SendMoneyToAfricaFlowViewModel

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipientScreen(
    navController: NavController
) {

    val activity = LocalContext.current as ComponentActivity
    val viewModel: SendMoneyToAfricaFlowViewModel =
        viewModel(viewModelStoreOwner = activity, factory = SendMoneyToAfricaFlowViewModel.Factory)

    val searchRecipientQuery by viewModel.searchRecipientQuery.observeAsState("")
    val recipientPhoneNumberState by viewModel.recipientPhoneNumber.observeAsState("")
    val recipientFirstNameState by viewModel.recipientFirstName.observeAsState("")
    val recipientLastNameState by viewModel.recipientLastName.observeAsState("")

    val canContinue = derivedStateOf {
        recipientPhoneNumberState.isNotBlank() && recipientFirstNameState.isNotBlank() && recipientLastNameState.isNotBlank()
    }

    var selectedOption by remember { mutableStateOf(SegmentedControlValue.PreviousRecipients) }

    Surface(color = Color.White) {
        Column {
            TopNavBar(upPress = { navController.popBackStack() })
            Column(
                Modifier.weight(1f)
            ) {
                Spacer(Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.recipient_screen_title),
                    style = HomeStyle,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(24.dp))

                SegmentedControl(
                    options = listOf(
                        SegmentedControlValue.PreviousRecipients, SegmentedControlValue.NewRecipient
                    ),
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it },
                    modifier = Modifier.padding(horizontal = 24.dp),
                )
                Spacer(Modifier.height(24.dp))
                BasicTextField(
                    value = searchRecipientQuery,
                    onValueChange = {
                        viewModel.onSearchRecipientQueryChange(
                            it
                        )
                    },
                    singleLine = true,
                    cursorBrush = SolidColor(Primary100),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .background(Gray05, RoundedCornerShape(12.dp))
                                .height(40.dp)
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Box {
                                innerTextField()

                                if (searchRecipientQuery.isEmpty()) {
                                    Text(
                                        stringResource(R.string.search_recipient_litteral),
                                        color = Color.Gray
                                    )
                                }
                            }

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(32.dp))

                if (selectedOption == SegmentedControlValue.PreviousRecipients) {
                    PhoneRecipientList(
                        searchState = searchRecipientQuery,
                        navController = navController,
                        viewModel = viewModel
                    )
                } else {
                    RecipientDetails(recipientPhoneNumberState = recipientPhoneNumberState,
                        recipientFirstNameState = recipientFirstNameState,
                        recipientLastNameState = recipientLastNameState,
                        onRecipientPhoneNumberChange = {
                            viewModel.onRecipientPhoneNumberChange(it)
                        },
                        onRecipientFirstNameChange = {
                            viewModel.onRecipientFirstNameChange(it)
                        },
                        onRecipientLastNameChange = {
                            viewModel.onRecipientLastNameChange(it)
                        })

                }
            }
            if (selectedOption == SegmentedControlValue.NewRecipient) {
                Box(
                    Modifier
                        .shadow(elevation = 24.dp)
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    RemitButton(
                        text = stringResource(id = R.string.continue_litteral),
                        enabled = canContinue.value,
                        onClick = {
                            var recipient = Recipient(
                                id = "0",
                                name = "${recipientFirstNameState.trim()} ${recipientLastNameState.trim()}",
                                phoneNumber = "+229 ${recipientPhoneNumberState.trim()}",
                                country = "Benin",
                                mobileWallet = ""
                            )

                            viewModel._selectedRecipient.value = recipient
                            navController.navigate(Destinations.MOBILE_WALLETS_ROUTE)

                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
fun PhoneRecipientList(
    searchState: String, navController: NavController, viewModel: SendMoneyToAfricaFlowViewModel
) {
    val recipients = viewModel.recipients.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadRecipients()
    }

    Column {
        Text(
            "Contacts on your phone",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(Modifier.height(16.dp))

        Divider(color = Gray05)

        when (val result = recipients.value) {
            is NetworkResult.Success -> LazyColumn(Modifier.wrapContentHeight(unbounded = false)) {
                itemsIndexed(items = result.data.filterRecipients(searchState)) { index, recipient ->

                    recipient.phoneNumber =
                        "+229 98 767 289"//we don't receive phoneNumber from the backend API
                    ContactItem(imageUrl = "https://i.pravatar.cc/300",
                        navController = navController,
                        recipient = recipient,
                        onClick = {
                            viewModel._selectedRecipient.value = recipient
                            navController.navigate(Destinations.MOBILE_WALLETS_ROUTE)
                        })
                }
            }

            is NetworkResult.Error -> Text(result.exception.message ?: "Unknown Error")
            NetworkResult.Loading -> CircularProgressIndicator(
                Modifier
                    .padding(56.dp)
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        if (recipients.value is NetworkResult.Success) {
            if (searchState.isEmpty() && (recipients.value as NetworkResult.Success<List<Recipient>>).data.filterRecipients(
                    searchState
                )
                    .isEmpty()
            ) {
                CircularProgressIndicator(
                    Modifier
                        .padding(56.dp)
                        .size(32.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else if ((recipients.value as NetworkResult.Success<List<Recipient>>).data.filterRecipients(
                    searchState
                ).isEmpty()
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.illustration_empty),
                        contentDescription = null,
                        modifier = Modifier.padding(40.dp)
                    )
                    Spacer(Modifier.size(8.dp))
                    Text(
                        stringResource(R.string.no_matching_results_found),
                        color = Gray100,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}


fun List<Recipient>.filterRecipients(query: String): List<Recipient> {
    return if (query.isEmpty()) this else filter { recipient ->
        recipient.name.contains(query, ignoreCase = true) || recipient.phoneNumber!!.contains(
            query,
            ignoreCase = true
        )
    }
}

@Composable
fun RecipientDetails(
    recipientPhoneNumberState: String,
    recipientFirstNameState: String,
    recipientLastNameState: String,
    onRecipientPhoneNumberChange: (String) -> Unit,
    onRecipientFirstNameChange: (String) -> Unit,
    onRecipientLastNameChange: (String) -> Unit,
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Column(
            Modifier.padding(horizontal = 24.dp)

        ) {
            Text("Country", fontSize = 16.sp, color = Gray100)
            Spacer(Modifier.height(8.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .border(
                        color = Gray25, width = 1.dp, shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_flag_benin),
                        contentDescription = "Country flag",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.size(8.dp))
                    Text(text = "Bénin", color = Gray100, fontSize = 16.sp)
                    Spacer(Modifier.weight(1f))
                    Text(text = "+229", color = Gray50, fontSize = 16.sp)
                    Spacer(Modifier.size(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.chevron_down),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(Modifier.size(16.dp))


            RemitIconButton(
                text = "Choose a contact",
                onClick = {},
                iconRes = R.drawable.document,
                borderColor = Primary15,
                backgroundColor = Primary05,
            )
            Spacer(Modifier.height(32.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(color = Gray05, modifier = Modifier.weight(1f))
                Text(
                    stringResource(R.string.or_add_manually),
                    maxLines = 1,
                    color = Gray50,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .width(IntrinsicSize.Max)
                )
                Divider(color = Gray05, modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(24.dp))
            Text(stringResource(R.string.phone_nubmer_label), fontSize = 16.sp, color = Gray100)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = recipientPhoneNumberState,
                onValueChange = onRecipientPhoneNumberChange,
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Text(stringResource(R.string.first_name_label), fontSize = 16.sp, color = Gray100)

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = recipientFirstNameState,
                onValueChange = onRecipientFirstNameChange,
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Text(stringResource(R.string.last_name_label), fontSize = 16.sp, color = Gray100)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = recipientLastNameState,
                onValueChange = onRecipientLastNameChange,
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

        }
    }
}

object SegmentedControlValue {
    const val PreviousRecipients = "Previous recipients"
    const val NewRecipient = "New recipient"
}