package ar.com.depietro.tuiter.data.user.di

import ar.com.depietro.tuiter.data.user.network.UserAPI
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataProvider {
    @Provides
    @Singleton
    fun provideUserAPI(gson: Gson): UserAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://10.0.2.2:3000/v1/")
            .build()
            .create(UserAPI::class.java)
    }

}