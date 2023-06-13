package ar.com.depietro.tuiter.data.tuit.network

import ar.com.depietro.tuiter.data.tuit.model.Tuit
import com.google.gson.annotations.SerializedName

data class TuitDTO(
    val id: Int = 0,
    val message: String = "",

    @SerializedName("author_id")
    val authorId: String = "",
    val date: String = ""
)

fun TuitDTO.asModel() = Tuit(
    id = id,
    message = message,
    date = date,
)

data class TuitCreateDTO(
    val message: String = "",
    val authorId: Int = 0,
)