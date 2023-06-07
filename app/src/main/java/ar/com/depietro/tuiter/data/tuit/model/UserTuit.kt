package ar.com.depietro.tuiter.data.tuit.model

data class UserTuit(
    val Id: Int = 0,
    val AvatarUrl: String = "",
    val Message: String = "",
    val Author: String = "",
    val Date: String = "",
    val Liked: Boolean = false,
    val Likes: Int = 0
)
