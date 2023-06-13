package ar.com.depietro.tuiter.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseProvider {

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