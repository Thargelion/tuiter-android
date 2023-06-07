package ar.com.depietro.tuiter.data.tuit.network


data class UserTuitDTO(
    val id: Int = 0,
    val avatarUrl: String = "",
    val message: String = "",
    val author: String = "",
    val date: String = "",
    val liked: Boolean = false,
    val likes: Int = 0
)
