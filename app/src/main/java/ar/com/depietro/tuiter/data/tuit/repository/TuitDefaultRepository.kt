package ar.com.depietro.tuiter.data.tuit.repository

import ar.com.depietro.tuiter.data.tuit.repository.TuitRepository
import ar.com.depietro.tuiter.data.tuit.model.Tuit
import kotlinx.coroutines.flow.Flow

class TuitDefaultRepository : TuitRepository {
    override fun getTuit(tuitId: Int): Flow<Tuit> {
        TODO("Not yet implemented")
    }

    override fun getList(): Flow<List<Tuit>> {
        TODO("Not yet implemented")
    }

    override fun getPagedList(page: Int): Flow<List<Tuit>> {
        TODO("Not yet implemented")
    }

}
