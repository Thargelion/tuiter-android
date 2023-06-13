package ar.com.depietro.tuiter.data.tuit.model

data class UserTuit(
    val id: Int = 0,
    val avatarUrl: String = "",
    val message: String = "",
    val author: String = "",
    val date: String = "",
    var liked: Boolean = false,
    val likes: Int = 0
)
