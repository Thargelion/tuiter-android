package ar.com.depietro.tuiter.data.tuit.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTuitDAO {

    @Query("SELECT * FROM user_tuits LIMIT 20")
    fun listUserTuits(): Flow<List<UserTuitEntity>>

    @Upsert
    suspend fun addUserTuitList(userTuitList: List<UserTuitEntity>)

}