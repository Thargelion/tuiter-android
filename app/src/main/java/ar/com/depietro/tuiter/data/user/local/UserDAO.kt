package ar.com.depietro.tuiter.data.user.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM users WHERE userName LIKE :userName")
    fun findByName(userName: String): Flow<UserEntity>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun findById(userId: Int): Flow<UserEntity>
}