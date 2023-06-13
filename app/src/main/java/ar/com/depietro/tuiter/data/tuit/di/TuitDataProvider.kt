package ar.com.depietro.tuiter.data.tuit.di

import ar.com.depietro.tuiter.data.tuit.network.TuitAPI
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
object TuitDataProvider {

    @Provides
    @Singleton
    fun provideTuitAPI(gson: Gson): TuitAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://10.0.2.2:3000/v1/")
            .build()
            .create(TuitAPI::class.java)
    }

}