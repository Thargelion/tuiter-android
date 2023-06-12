package ar.com.depietro.tuiter.data.tuit.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import ar.com.depietro.tuiter.data.local.TuiterDatabase
import ar.com.depietro.tuiter.data.tuit.local.TuitDAO
import ar.com.depietro.tuiter.data.tuit.local.UserTuitDAO
import ar.com.depietro.tuiter.data.tuit.local.UserTuitEntity
import ar.com.depietro.tuiter.data.tuit.local.asModel
import ar.com.depietro.tuiter.data.tuit.model.Tuit
import ar.com.depietro.tuiter.data.tuit.model.UserTuit
import ar.com.depietro.tuiter.data.tuit.network.TuitHTTP
import ar.com.depietro.tuiter.data.tuit.network.asModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TuitDefaultRepository @Inject constructor(
    tuitDatabase: TuiterDatabase,
    private val tuitHTTP: TuitHTTP
) : TuitRepository {
    private val tuitDAO: TuitDAO = tuitDatabase.tuitDao()
    private val userTuitDao: UserTuitDAO = tuitDatabase.userTuitDao()
    override fun getTuit(tuitId: Int): Flow<Tuit> {
        return flow {
            tuitDAO.findById(tuitId)
                .map { it.asModel() }
                .collect {
                    emit(it)
                }

            tuitHTTP.getTuitById(tuitId)
                .map { it.asModel() }
                .collect {
                    emit(it)
                }
        }
    }

    override fun getPagedUserTuits(userId: Int) = Pager(
        PagingConfig(pageSize = 20, prefetchDistance = 20)
    ) {
        PagingUserPostSource(tuitHTTP, userId)
    }.flow
}

fun UserTuit.asEntity() = UserTuitEntity(
    id = id,
    avatarUrl = avatarUrl,
    message = message,
    author = author,
    date = date,
    liked = liked,
    likes = likes
)

