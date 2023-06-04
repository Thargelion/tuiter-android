package ar.com.depietro.tuiter.data.tuit.network

import retrofit2.http.Body
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

}