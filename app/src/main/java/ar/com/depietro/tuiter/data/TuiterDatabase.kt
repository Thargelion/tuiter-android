package ar.com.depietro.tuiter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.com.depietro.tuiter.data.user.local.UserDAO
import ar.com.depietro.tuiter.data.user.local.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class TuiterDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}