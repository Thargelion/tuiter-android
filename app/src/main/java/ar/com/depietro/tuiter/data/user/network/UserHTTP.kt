package ar.com.depietro.tuiter.data.user.network

import kotlinx.coroutines.flow.Flow

interface UserHTTP {
    fun getUserById(id: Int): Flow<UserDTO>
    fun getUserByName(name: String): Flow<UserDTO>

    fun createUser(user: UserCreateDTO): Flow<UserDTO>
}