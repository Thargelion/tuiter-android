package ar.com.depietro.tuiter.data.tuit.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.com.depietro.tuiter.data.tuit.model.Tuit

@Entity(tableName = "tuits")
data class TuitEntity(
    @PrimaryKey
    val id: Int = 0,
    val message: String = "",

    @ColumnInfo(name = "author_id")
    val authorId: String = "",
    val date: String = "",
)

fun TuitEntity.asModel() = Tuit(
    id = id,
    message = message,
    authorId = authorId,
    date = date
)