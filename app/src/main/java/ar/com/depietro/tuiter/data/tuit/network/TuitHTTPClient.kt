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

    override suspend fun listPageUserTuits(userId: Int, page: Int): List<UserTuitDTO> {
        return http.listPageUserTuits(userId, page)
    }

    override fun likeTuit(likeDTO: LikeDTO): Flow<UserTuitDTO> {
        return flow {
            emit(http.likeTuit(likeDTO))
        }
    }

    override fun dislikeTuit(likeDTO: LikeDTO): Flow<UserTuitDTO> {
        return flow {
            emit(http.dislikeTuit(likeDTO))
        }
    }
}