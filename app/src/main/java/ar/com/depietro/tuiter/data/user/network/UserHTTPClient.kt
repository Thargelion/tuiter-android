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

    override fun createUser(user: UserCreateDTO): Flow<UserDTO> {
        return flow {
            emit(http.createUser(user))
        }.catch { e ->
            Log.e("TUITER_CLIENT", e.message ?: "Error creating user")
            Log.d("TUITER_CLIENT", user.toString())
            throw Exception("Error creating user")
        }
    }
}