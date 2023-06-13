package ar.com.depietro.tuiter.data.user.repository

import ar.com.depietro.tuiter.data.local.TuiterDatabase
import ar.com.depietro.tuiter.data.user.local.UserDAO
import ar.com.depietro.tuiter.data.user.local.asModel
import ar.com.depietro.tuiter.data.user.model.User
import ar.com.depietro.tuiter.data.user.network.UserHTTP
import ar.com.depietro.tuiter.data.user.network.asModel
import ar.com.depietro.tuiter.data.user.network.asCreateDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDefaultRepository @Inject constructor(
    userDatabase: TuiterDatabase,
    private val userHTTP: UserHTTP
) : UserRepository {
    private val userDAO: UserDAO = userDatabase.userDao()


    override fun getUser(userId: Int): Flow<User> {
        return flow {
            userDAO.findById(userId)
                .map { it.asModel() }
                .catch {
                    userHTTP.getUserById(userId)
                        .map { it.asModel() }
                        .collect {
                            emit(it)
                        }
                }
                .collect {
                    emit(it)
                }

            userHTTP.getUserById(userId)
                .map { it.asModel() }
                .collect {
                    emit(it)
                }
        }

    }

    override fun create(user: User): Flow<User> {
        return userHTTP.createUser(user.asCreateDTO()).map { it.asModel() }
    }
}