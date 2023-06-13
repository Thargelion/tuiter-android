package ar.com.depietro.tuiter.data.tuit.network

import ar.com.depietro.tuiter.data.tuit.model.Like
import com.google.gson.annotations.SerializedName

data class LikeDTO(
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("tuit_id")
    val tuitId: Int = 0,
)

fun LikeDTO.asModel() = Like(
    userId = userId,
    tuitId = tuitId,
)

fun Like.asDTO() = LikeDTO(
    userId = userId,
    tuitId = tuitId,
)