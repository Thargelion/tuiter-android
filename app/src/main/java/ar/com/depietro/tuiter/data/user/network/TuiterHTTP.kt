package ar.com.depietro.tuiter.data.user.network

import kotlinx.coroutines.flow.Flow

interface TuiterHTTP {
    fun getUserById(id: Int): Flow<UserNetworkDTO>
    fun createUser(user: UserCreateDTO): Flow<UserNetworkDTO>
}