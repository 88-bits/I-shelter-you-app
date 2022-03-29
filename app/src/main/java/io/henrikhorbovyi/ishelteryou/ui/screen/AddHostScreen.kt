package io.henrikhorbovyi.ishelteryou.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import io.henrikhorbovyi.ishelteryou.R
import io.henrikhorbovyi.ishelteryou.ui.entity.HostUi
import io.henrikhorbovyi.ishelteryou.ui.theme.Shapes

@Composable
fun AddHostScreen(
    modifier: Modifier = Modifier,
    onPublishClicked: (HostUi) -> Unit = {},
) {
    LazyColumn(modifier.padding(16.dp)) {
        item {
            Text(
                text = stringResource(R.string.publish_host_screen_title),
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            HostForm(
                onPublishClicked = onPublishClicked
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HostForm(
    onPublishClicked: (HostUi) -> Unit = {}
) {
    var hostView by remember { mutableStateOf(HostUi()) }

    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = hostView.name,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = ""
                )
            },
            label = { Text(text = stringResource(R.string.name_label)) },
            onValueChange = {
                hostView = hostView.copy(name = it)
            }
        )

        Text(
            text = stringResource(R.string.place_label),
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.place_country_label)) },
            value = hostView.place.country,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_coutry),
                    contentDescription = ""
                )
            },
            onValueChange = {
                hostView = hostView.copy(place = hostView.place.copy(country = it))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = hostView.place.city,
            label = { Text(text = stringResource(R.string.place_city_label)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_city),
                    contentDescription = ""
                )
            },
            onValueChange = {
                hostView = hostView.copy(place = hostView.place.copy(city = it))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = hostView.place.address,
            label = { Text(text = stringResource(R.string.place_address_label)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_address),
                    contentDescription = ""
                )
            },
            onValueChange = {
                hostView = hostView.copy(place = hostView.place.copy(address = it))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = hostView.place.postcode,
            label = { Text(text = stringResource(R.string.place_postcode_label)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_numbers),
                    contentDescription = ""
                )
            },
            onValueChange = {
                hostView = hostView.copy(place = hostView.place.copy(postcode = it))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = hostView.place.description,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_description),
                    contentDescription = ""
                )
            },
            label = { Text(text = stringResource(R.string.place_description_label)) },
            maxLines = 5,

            onValueChange = {
                hostView = hostView.copy(place = hostView.place.copy(description = it))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = (hostView.place.capacity.takeIf { it != 0 } ?: "").toString(),
            label = { Text(text = stringResource(R.string.people_capacity_label)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_people_capacity),
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                hostView =
                    hostView.copy(place = hostView.place.copy(capacity = it.toIntOrNull() ?: 0))
            }
        )

        Text(
            text = stringResource(R.string.your_contacts),
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = hostView.contact.phone,
            label = { Text(text = stringResource(R.string.phone_label)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = ""
                )
            },
            onValueChange = {
                hostView = hostView.copy(contact = hostView.contact.copy(phone = it))
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = hostView.contact.email,
            label = { Text(text = stringResource(R.string.email_label)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                hostView = hostView.copy(contact = hostView.contact.copy(email = it))
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = hostView.place.isPetFriendly,
                onCheckedChange = {
                    hostView = hostView.copy(place = hostView.place.copy(isPetFriendly = it))
                }
            )

            Text(
                text = stringResource(R.string.placel_pet_friendly_label),
                Modifier.clickable {
                    hostView = hostView
                        .copy(place = hostView.place.copy(isPetFriendly = !hostView.place.isPetFriendly))
                }
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            shape = Shapes.medium,
            onClick = { onPublishClicked(hostView) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Text(
                text = stringResource(R.string.publich_place_button_label),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
