package ar.com.depietro.tuiter.data.tuit.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TuitAPI {

    @GET("tuits/{tuitId}")
    suspend fun getTuitById(@Path("tuitId") id: Int): TuitDTO

    @GET("tuits")
    suspend fun listPageTuits(@Query("page") page: Int): List<TuitDTO>

    @POST
    suspend fun createTuit(@Body tuit: TuitCreateDTO): TuitDTO

    @GET("users/{userId}/tuits")
    suspend fun listPageUserTuits(
        @Path("userId") userId: Int,
        @Query("page") page: Int
    ): List<UserTuitDTO>

    @POST("likes")
    suspend fun likeTuit(@Body likeDTO: LikeDTO): UserTuitDTO

    @POST("dislikes")
    suspend fun dislikeTuit(@Body likeDTO: LikeDTO): UserTuitDTO

}