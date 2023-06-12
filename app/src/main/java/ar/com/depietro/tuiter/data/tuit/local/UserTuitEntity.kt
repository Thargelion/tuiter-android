package ar.com.depietro.tuiter.data.tuit.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.com.depietro.tuiter.data.tuit.model.UserTuit

@Entity(tableName = "user_tuits")
data class UserTuitEntity(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String = "",
    val message: String = "",
    val author: String = "",
    val date: String = "",
    val liked: Boolean = false,
    val likes: Int = 0
)

fun UserTuitEntity.asModel() = UserTuit(
    id = id,
    avatarUrl = avatarUrl,
    message = message,
    author = author,
    date = date,
    liked = liked,
    likes = likes
)