package ar.com.depietro.tuiter.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TuiterSnackbar(onClick: () -> Unit, actionText: String, message: String) {
    val scope = rememberCoroutineScope()
    Snackbar(
        action = {
            Button(onClick = onClick) {
                Text(actionText)
            }
        },
        modifier = Modifier.padding(8.dp)
    ) { Text(text = message) }
}