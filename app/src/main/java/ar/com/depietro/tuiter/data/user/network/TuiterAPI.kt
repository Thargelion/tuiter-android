package ar.com.depietro.tuiter.data.user.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TuiterAPI {
    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") id: Int): UserNetworkDTO

    @POST("users")
    suspend fun createUser(@Body user: UserCreateDTO): UserNetworkDTO
}