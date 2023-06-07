package ar.com.depietro.tuiter.data.tuit.repository

import ar.com.depietro.tuiter.data.tuit.model.Tuit
import ar.com.depietro.tuiter.data.tuit.model.UserTuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {
    fun getTuit(tuitId: Int): Flow<Tuit>
    fun getPagedList(page: Int): Flow<List<Tuit>>
    fun getPagedUserTuits(userId: Int, page: Int): Flow<List<UserTuit>>
}