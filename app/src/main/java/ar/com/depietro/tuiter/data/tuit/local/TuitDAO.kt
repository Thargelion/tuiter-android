package ar.com.depietro.tuiter.data.tuit.local

import androidx.room.Dao
import androidx.room.Query
import ar.com.depietro.tuiter.data.tuit.model.Tuit
import kotlinx.coroutines.flow.Flow

@Dao
interface TuitDAO {

    @Query("SELECT * FROM tuits WHERE id = :tuitId")
    fun findById(tuitId: Int): Flow<TuitEntity>

    @Query("SELECT * FROM tuits LIMIT 20")
    fun listPageTuits(): Flow<List<TuitEntity>>

}