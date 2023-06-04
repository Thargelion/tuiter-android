package ar.com.depietro.tuiter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class TuitData(
    val id: Int = 0,
    val message: String = "",
    val author_name: String = "",
    val avatarUrl: String = "",
    val isLiked: Boolean = false,
    val date: String = "",
    val likeAction: (id: Int) -> Unit,
)

@Composable
fun Tuit(tuitData: TuitData) {
    // Thanks https://www.develou.com/cards-en-jetpack-compose/
    val paddingModifier = Modifier.padding(8.dp)
    Card {
        Column() {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = CircleShape)
                        .size(40.dp)
                ) {
                    AsyncImage(
                        model = tuitData.avatarUrl,
                        contentDescription = "avatar",
                    )
                }
                Column(Modifier.fillMaxWidth()) {
                    Column {
                        Text(
                            text = tuitData.author_name,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

                }
            }
            Row {
                Box(
                    paddingModifier
                        .fillMaxWidth(),
                ) {
                    Column {
                        Row {
                            Text(
                                text = tuitData.message,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Row {
                            Text(
                                text = tuitData.date,
                                style = TextStyle(color = Color.Gray, fontSize = 10.sp),
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
            Row {
                IconButton(onClick = { tuitData.likeAction(tuitData.id) }) {
                    if (tuitData.isLiked) {
                        Icon(Icons.Default.Favorite, contentDescription = "Like")
                    } else {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TuitPreview() {
    Tuit(TuitData(
        id = 1,
        message = "Hola soy un mensaje escrito por un usuario y tengo un montón" +
                " de texto para mostraraaaaaa aaaaaaaa aaaaaaaaaaaaa aaaaaa aaaaaaaaaa" +
                "aaaaaaaaaaaaa aaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaa",
        avatarUrl = "https://ui-avatars.com/api/?background=random&name=John+Doe",
        author_name = "Jorge",
        date = "5 de Mayo de 2021",
        isLiked = false,
        likeAction = { id -> println(id) }
    ))
}