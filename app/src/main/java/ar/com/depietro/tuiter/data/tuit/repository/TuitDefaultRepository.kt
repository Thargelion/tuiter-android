package ar.com.depietro.tuiter.data.tuit.repository


import ar.com.depietro.tuiter.data.local.TuiterDatabase
import ar.com.depietro.tuiter.data.tuit.local.TuitDAO
import ar.com.depietro.tuiter.data.tuit.local.asModel
import ar.com.depietro.tuiter.data.tuit.model.Tuit
import ar.com.depietro.tuiter.data.tuit.model.UserTuit
import ar.com.depietro.tuiter.data.tuit.network.TuitHTTP
import ar.com.depietro.tuiter.data.tuit.network.asModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TuitDefaultRepository @Inject constructor(
    tuitDatabase: TuiterDatabase,
    private val tuitHTTP: TuitHTTP
) : TuitRepository {
    private val tuitDAO: TuitDAO = tuitDatabase.tuitDao()
    override fun getTuit(tuitId: Int): Flow<Tuit> {
        return flow {
            tuitDAO.findById(tuitId)
                .catch {
                    tuitHTTP.getTuitById(tuitId)
                        .map { it.asModel() }
                        .collect {
                            emit(it)
                        }
                }

            tuitHTTP.getTuitById(tuitId)
                .map { it.asModel() }
                .collect {
                    emit(it)
                }
        }
    }

    override fun getPagedList(page: Int): Flow<List<Tuit>> {
        return flow {
            tuitDAO.listPageTuits()
                .catch {
                    tuitHTTP.listPageTuits(page)
                        .map {
                            it.listIterator().asSequence().map { item -> item.asModel() }.toList()
                        }
                        .collect {
                            emit(it)
                        }
                }
                .map {
                    it.listIterator().asSequence().map { item -> item.asModel() }.toList()
                }
                .collect {
                    emit(it)
                }

            tuitHTTP.listPageTuits(page)
                .map {
                    it.listIterator().asSequence().map { item -> item.asModel() }.toList()
                }
                .collect {
                    emit(it)
                }
        }
    }


    override fun getPagedUserTuits(userId: Int, page: Int): Flow<List<UserTuit>> {
        TODO("Not yet implemented")
    }
}
