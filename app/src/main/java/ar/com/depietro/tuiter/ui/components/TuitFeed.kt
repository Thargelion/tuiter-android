package ar.com.depietro.tuiter.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable

@Composable
fun TuitFeed(tuitList: List<TuitData>, loadMore: () -> Unit) {
    val state = rememberLazyListState()
    LazyColumn(state = state) {
        items(tuitList.size) {
            Tuit(tuitList[it])
        }
    }
    InfiniteList(listState = state, onLoadMore = loadMore)
}