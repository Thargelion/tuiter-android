package ar.com.depietro.tuiter.data.tuit.repository

import ar.com.depietro.tuiter.data.tuit.model.Tuit
import kotlinx.coroutines.flow.Flow

interface TuitRepository {

    fun getTuit(tuitId: Int): Flow<Tuit>

    fun getList(): Flow<List<Tuit>>

    fun getPagedList(page: Int): Flow<List<Tuit>>
}