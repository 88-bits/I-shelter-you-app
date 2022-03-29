package io.henrikhorbovyi.ishelteryou.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HostDetailScreen(
    hostId: String? = ""
) {
    Column {
        Text(text = "Yay! $hostId")
    }
}
