package ar.com.depietro.tuiter.data.tuit.network

import kotlinx.coroutines.flow.Flow

interface TuitHTTP {
    fun getTuitById(id: Int): Flow<TuitDTO>
    fun createTuit(tuit: TuitCreateDTO): Flow<TuitDTO>
    fun listPageTuits(page: Int): Flow<List<TuitDTO>>
    suspend fun listPageUserTuits(userId: Int, page: Int): List<UserTuitDTO>

    fun likeTuit(likeDTO: LikeDTO): Flow<UserTuitDTO>

    fun dislikeTuit(likeDTO: LikeDTO): Flow<UserTuitDTO>
}