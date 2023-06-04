package ar.com.depietro.tuiter.data.user.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {
    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") id: Int): UserDTO

    @POST("users")
    suspend fun createUser(@Body user: UserCreateDTO): UserDTO
}