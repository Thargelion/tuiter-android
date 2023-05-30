package ar.com.depietro.tuiter.data.user.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.com.depietro.tuiter.data.user.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val userName: String,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String
)

fun UserEntity.asModel() = User(
    id = id,
    userName = userName,
    avatarUrl = avatarUrl
)