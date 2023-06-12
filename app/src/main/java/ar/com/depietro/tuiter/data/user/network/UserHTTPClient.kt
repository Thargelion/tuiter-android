package ar.com.depietro.tuiter.data.user.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserHTTPClient @Inject constructor(private val http: UserAPI) : UserHTTP {
    override fun getUserById(id: Int): Flow<UserDTO> {
        return flow {
            emit(http.getUserById(id))
        }
    }

    override fun getUserByName(name: String): Flow<UserDTO> {
        return flow {
            emit(http.getUsers(name).first())
        }
    }

    override fun createUser(user: UserCreateDTO): Flow<UserDTO> {
        return flow {
            getUserByName(user.userName).catch {
                emit(http.createUser(user))
            }.collect {
                emit(it)
            }
        }
    }
}