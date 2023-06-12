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

data class TuitViewData(
    val id: Int = 0,
    val message: String = "",
    val authorName: String = "",
    val avatarUrl: String = "",
    val liked: Boolean = false,
    val likes: Int = 0,
    val Date: String = "",
)

@Composable
fun Tuit(tuitData: TuitViewData, likeAction: (id: Int) -> Unit, modifier: Modifier = Modifier) {
    // Thanks https://www.develou.com/cards-en-jetpack-compose/
    val paddingModifier = Modifier.padding(8.dp)
    Card(modifier = modifier) {
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
                            text = tuitData.authorName,
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
                                text = tuitData.Date,
                                style = TextStyle(color = Color.Gray, fontSize = 10.sp),
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
            Row {
                IconButton(onClick = { likeAction(tuitData.id) }) {
                    if (tuitData.liked) {
                        Icon(Icons.Default.Favorite, contentDescription = "Like")
                    } else {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
                    }
                }
                Box(modifier = paddingModifier.align(alignment = Alignment.CenterVertically)) {
                    Text(
                        text = tuitData.likes.toString()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TuitPreview() {
    Tuit(TuitViewData(
        id = 1,
        message = "Hola soy un mensaje escrito por un usuario y tengo un montón" +
                " de texto para mostraraaaaaa aaaaaaaa aaaaaaaaaaaaa aaaaaa aaaaaaaaaa" +
                "aaaaaaaaaaaaa aaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaa",
        avatarUrl = "https://ui-avatars.com/api/?background=random&name=John+Doe",
        authorName = "Jorge",
        Date = "5 de Mayo de 2021",
        liked = false,
        likes = 0,
    ), likeAction = { id -> println(id) })
}