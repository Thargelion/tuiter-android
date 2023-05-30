package ar.com.depietro.tuiter.data.user.network

import ar.com.depietro.tuiter.data.user.model.User
import com.google.gson.annotations.SerializedName

data class UserCreateDTO(
    @SerializedName("name")
    val userName: String
)

fun UserCreateDTO.asModel() = User(
    userName = userName
)

fun User.asCreateDTO() = UserCreateDTO(
    userName = userName
)

data class UserNetworkDTO(
    @SerializedName("id")
    val userId: Int,
    @SerializedName("name")
    val userName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

fun UserNetworkDTO.asModel() = User(
    id = userId,
    userName = userName,
    avatarUrl = avatarUrl
)

