package ar.com.depietro.tuiter.ui.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginUser(modifier: Modifier = Modifier) {
    Card(modifier) {
        Text("Username")
        TextField(
            label = { Text(text = "Username") },
            value = ""
        )
    }
}

@Preview
@Composable
private fun loginPreview() {
    LoginUser()
}