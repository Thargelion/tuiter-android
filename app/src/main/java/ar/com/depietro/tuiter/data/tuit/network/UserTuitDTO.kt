package ar.com.depietro.tuiter.data.tuit.network

import ar.com.depietro.tuiter.data.tuit.model.UserTuit


data class UserTuitDTO(
    val id: Int = 0,
    val avatarUrl: String = "",
    val message: String = "",
    val author: String = "",
    val date: String = "",
    val liked: Boolean = false,
    val likes: Int = 0
)

fun UserTuitDTO.asModel() = UserTuit(
    id = id,
    avatarUrl = avatarUrl,
    message = message,
    author = author,
    date = date,
    liked = liked,
    likes = likes
)