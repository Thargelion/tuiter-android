package ar.com.depietro.tuiter.data.tuit.repository

import androidx.paging.PagingData
import ar.com.depietro.tuiter.data.tuit.model.Like
import ar.com.depietro.tuiter.data.tuit.model.Tuit
import ar.com.depietro.tuiter.data.tuit.model.UserTuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {
    fun getTuit(tuitId: Int): Flow<Tuit>
    fun getPagedUserTuits(userId: Int): Flow<PagingData<UserTuit>>
    fun addLike(like: Like): Flow<UserTuit>
    fun removeLike(like: Like): Flow<UserTuit>
}