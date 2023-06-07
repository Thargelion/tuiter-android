package ar.com.depietro.tuiter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.com.depietro.tuiter.data.tuit.local.TuitDAO
import ar.com.depietro.tuiter.data.tuit.local.TuitEntity
import ar.com.depietro.tuiter.data.tuit.local.UserTuitDAO
import ar.com.depietro.tuiter.data.tuit.local.UserTuitEntity
import ar.com.depietro.tuiter.data.user.local.UserDAO
import ar.com.depietro.tuiter.data.user.local.UserEntity

@Database(
    entities = [UserEntity::class, TuitEntity::class, UserTuitEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TuiterDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    abstract fun tuitDao(): TuitDAO

    abstract fun userTuitDao(): UserTuitDAO
}