package ar.com.depietro.tuiter.data.user.network

import ar.com.depietro.tuiter.data.user.model.User

data class AuthorNetworkDTO(
    val id: String, val userName: String, val avatarUrl: String
)

fun AuthorNetworkDTO.asModel() = User(
    id = id, userName = userName, avatarUrl = avatarUrl
)