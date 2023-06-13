package ar.com.depietro.tuiter.data.tuit.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ar.com.depietro.tuiter.data.tuit.model.UserTuit
import ar.com.depietro.tuiter.data.tuit.network.TuitHTTP
import ar.com.depietro.tuiter.data.tuit.network.asModel

class PagingUserPostSource(
    private val tuitRepository: TuitHTTP,
    val userId: Int,
) : PagingSource<Int, UserTuit>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserTuit> {
        return try {
            val page = params.key ?: 1

            val response = tuitRepository.listPageUserTuits(userId, page)
            LoadResult.Page(
                data = response.map { it.asModel() },
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserTuit>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}