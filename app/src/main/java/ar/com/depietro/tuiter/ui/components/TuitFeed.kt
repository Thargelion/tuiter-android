package ar.com.depietro.tuiter.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable

@Composable
fun TuitFeed(tuitList: List<TuitViewData>, loadMore: () -> Unit, likeAction: (id: Int) -> Unit) {
    val state = rememberLazyListState()
    LazyColumn(state = state) {
        items(tuitList.size) {
            Tuit(tuitList[it], likeAction)
        }
    }
    InfiniteList(listState = state, onLoadMore = loadMore)
}