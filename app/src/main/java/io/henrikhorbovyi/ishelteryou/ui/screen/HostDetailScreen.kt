package io.henrikhorbovyi.ishelteryou.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.henrikhorbovyi.ishelteryou.state.HostUiState
import io.henrikhorbovyi.ishelteryou.ui.entity.HostUi
import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel

@Composable
fun HostDetailScreen(
    hostId: String? = "",
    hostsViewModel: HostsViewModel = viewModel(),
) {

    val hostState by hostsViewModel.hostByIdState.collectAsState()

    LaunchedEffect(hostId) {
        hostsViewModel.getHostById(hostId)
    }

    when (val state = hostState) {
        is HostUiState.Loading -> ProgressIndicator()
        is HostUiState.Error -> { /* >> todo << */ }
        is HostUiState.Loaded -> Content(state.host)
    }
}

@Composable
private fun Content(host: HostUi) {
    LazyColumn(Modifier.padding(16.dp)) {
        item {
            Text(
                text = "${host.name} place",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            host.place.run {
                DetailSection(label = "Country", value = country)
                DetailSection(label = "City", value = city)
                DetailSection(
                    label = "Full Address",
                    value = address,
                    onClick = { println("open maps") })
                DetailSection(label = "Postcode", value = postcode)
                DetailSection(label = "Description", value = description)
                DetailSection(label = "People capacity", value = capacity.toString())
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
