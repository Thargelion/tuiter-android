package ar.com.depietro.tuiter.data.tuit.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TuitHTTPClient @Inject constructor(private val http: TuitAPI) : TuitHTTP {
    override fun getTuitById(id: Int): Flow<TuitDTO> {
        return flow {
            emit(http.getTuitById(id))
        }
    }

    override fun createTuit(tuit: TuitCreateDTO): Flow<TuitDTO> {
        return flow {
            emit(http.createTuit(tuit))
        }
    }

    override fun listPageTuits(page: Int): Flow<List<TuitDTO>> {
        return flow {
            emit(http.listPageTuits(page))
        }
    }
}