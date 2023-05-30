package ar.com.depietro.tuiter.data.user.model


data class User(
    val id: Int = 0,
    val userName: String,
    val avatarUrl: String = ""
)
