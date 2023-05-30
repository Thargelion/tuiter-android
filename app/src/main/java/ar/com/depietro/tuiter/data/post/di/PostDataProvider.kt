package ar.com.depietro.tuiter.data.post.di

import android.content.Context
import androidx.room.Room
import ar.com.depietro.tuiter.data.TuiterDatabase
import ar.com.depietro.tuiter.data.user.network.TuiterAPI
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostDataProvider {
    @Provides
    @Singleton
    fun provideTuiterAPI(gson: Gson): TuiterAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://10.0.2.2:3000/v1/")
            .build()
            .create(TuiterAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TuiterDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TuiterDatabase::class.java,
            "tuiter_database"
        ).build()
    }

}