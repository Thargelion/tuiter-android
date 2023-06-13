package ar.com.depietro.tuiter.data.tuit.model

data class Tuit(
    val id: Int = 0,
    val message: String = "",
    val authorId: String = "",
    val date: String = "",
    val likes: Int = 0,
)