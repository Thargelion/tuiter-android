package ar.com.depietro.tuiter.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import ar.com.depietro.tuiter.data.tuit.model.UserTuit
import ar.com.depietro.tuiter.ui.screens.ListState
import ar.com.depietro.tuiter.ui.screens.asUserTuitViewModel

@Composable
fun TuitFeed(
    tuitList: LazyPagingItems<UserTuit>,
    likeAction: (id: Int) -> Unit,
    listState: ListState
) {
    LazyColumn {
        items(count = tuitList.itemCount) { index ->
            tuitList[index]?.let { tuit ->
                Tuit(
                    tuitData = tuit.asUserTuitViewModel(),
                    likeAction = likeAction,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
        item(key = listState) {
            when (listState) {
                ListState.LOADING -> {
                    CircularProgressIndicator()
                }

                ListState.ERROR -> Error()
                ListState.PAGINATING -> {
                    CircularProgressIndicator(color = Color.Black)
                }

                else -> {}
            }
        }
    }
}