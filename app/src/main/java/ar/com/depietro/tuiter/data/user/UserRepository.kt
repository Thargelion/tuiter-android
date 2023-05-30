package ar.com.depietro.tuiter.data.user

import ar.com.depietro.tuiter.data.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userId: Int): Flow<User>
    fun create(user: User): Flow<User>
}