package ar.com.depietro.tuiter.data.user.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface UserAPI {
    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") id: Int): UserDTO

    @GET("users")
    suspend fun getUsers(@Query("name") name: String): List<UserDTO>

    @POST("users")
    suspend fun createUser(@Body user: UserCreateDTO): UserDTO
}