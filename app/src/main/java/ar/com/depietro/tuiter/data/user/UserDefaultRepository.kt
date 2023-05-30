package ar.com.depietro.tuiter.data.user

import ar.com.depietro.tuiter.data.TuiterDatabase
import ar.com.depietro.tuiter.data.user.local.UserDAO
import ar.com.depietro.tuiter.data.user.local.asModel
import ar.com.depietro.tuiter.data.user.model.User
import ar.com.depietro.tuiter.data.user.network.TuiterHTTP
import ar.com.depietro.tuiter.data.user.network.asModel
import ar.com.depietro.tuiter.data.user.network.asCreateDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDefaultRepository @Inject constructor(
    userDatabase: TuiterDatabase,
    private val tuiterHTTP: TuiterHTTP
) : UserRepository {
    private val userDAO: UserDAO = userDatabase.userDao()


    override fun getUser(userId: Int): Flow<User> {
        return flow {
            userDAO.findById(userId)
                .map { it.asModel() }
                .catch {
                    tuiterHTTP.getUserById(userId)
                        .map { it.asModel() }
                        .collect {
                            emit(it)
                        }
                }
                .collect {
                    emit(it)
                }

            tuiterHTTP.getUserById(userId)
                .map { it.asModel() }
                .collect {
                    emit(it)
                }
        }

    }

    override fun create(user: User): Flow<User> {
        return tuiterHTTP.createUser(user.asCreateDTO()).map { it.asModel() }
    }
}