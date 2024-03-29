package ar.com.depietro.tuiter.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUser(onClickAction: (username: String) -> Unit, modifier: Modifier = Modifier) {
    val paddingModifier = Modifier.padding(10.dp)
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Card(modifier = paddingModifier) {
        Column {

            Text("Username", modifier = paddingModifier)
            TextField(
                modifier = paddingModifier,
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text(text = "Choose a Username") },
            )
            Row(modifier = paddingModifier.align(alignment = Alignment.End)) {
                Button(
                    onClick = { onClickAction(text.text) },
                    content = { Text(text = "Access") }
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginPreview() {
    LoginUser(({ dummy -> dummy }))
}
