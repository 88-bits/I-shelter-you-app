package io.henrikhorbovyi.ishelteryou.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.henrikhorbovyi.ishelteryou.R
import io.henrikhorbovyi.ishelteryou.state.HostUiState
import io.henrikhorbovyi.ishelteryou.ui.entity.HostUi
import io.henrikhorbovyi.ishelteryou.ui.theme.Shapes
import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel

@Composable
fun HostDetailScreen(
    hostId: String? = "",
    hostsViewModel: HostsViewModel = viewModel(),
    onSendMessage: (phone: String) -> Unit = {},
    onSendEmail: (email: String) -> Unit = {},
    onOpenMap: (address: String) -> Unit = {},
) {

    val hostState by hostsViewModel.hostByIdState.collectAsState()

    LaunchedEffect(hostId) {
        hostsViewModel.getHostById(hostId)
    }

    when (val state = hostState) {
        is HostUiState.Loading -> ProgressIndicator()
        is HostUiState.Error -> { /* >> todo << */ }
        is HostUiState.Loaded -> Content(
            state.host,
            onSendMessage = onSendMessage,
            onSendEmail = onSendEmail,
            onOpenMap = onOpenMap
        )
    }
}

@Composable
private fun Content(
    host: HostUi,
    onSendMessage: (phone: String) -> Unit = {},
    onSendEmail: (email: String) -> Unit = {},
    onOpenMap: (address: String) -> Unit = {},
) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.user_name_template, host.name),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            host.place.run {
                DetailSection(
                    label = stringResource(id = R.string.place_country_label),
                    value = country
                )
                DetailSection(label = stringResource(id = R.string.place_city_label), value = city)
                DetailSection(
                    label = stringResource(id = R.string.place_address_label),
                    value = address,
                    onClick = onOpenMap
                )
                DetailSection(
                    label = stringResource(id = R.string.place_postcode_label),
                    value = postcode
                )
                DetailSection(
                    label = stringResource(id = R.string.place_description_label),
                    value = description
                )
                DetailSection(
                    label = stringResource(id = R.string.people_capacity_label),
                    value = capacity.toString()
                )
                if (isPetFriendly) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = stringResource(R.string.pet_friendly_note))
                        Icon(
                            painterResource(id = R.drawable.ic_pet),
                            stringResource(id = R.string.place_pet_friendly_label)
                        )
                    }
                }
            }
            host.contact.run {
                DetailSection(
                    label = stringResource(id = R.string.phone_label),
                    value = phone,
                    onClick = onSendMessage
                )
                DetailSection(
                    label = stringResource(id = R.string.email_label),
                    value = email,
                    onClick = onSendEmail
                )
            }

            // Actions
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSendMessage(host.contact.phone) },
                    shape = Shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    content = { Text(text = stringResource(R.string.send_message_button_label)) }
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSendEmail(host.contact.email) },
                    shape = Shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    content = { Text(text = stringResource(R.string.send_email_button_label)) }
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  onOpenMap(host.place.address) },
                    shape = Shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    content = { Text(text = stringResource(R.string.open_address_button_label)) }
                )
            }
        }
    }
}

@Composable
private fun DetailSection(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: ((String) -> Unit)? = null
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )

        SelectionContainer {
            ClickableText(
                text = buildAnnotatedString { append(value) },
                onClick = { onClick?.invoke(value) },
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
fun ProgressIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HostDetailsScreenPreview() {
    HostDetailScreen()
}
