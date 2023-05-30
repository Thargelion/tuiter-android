package ar.com.depietro.tuiter.data.user.di

import ar.com.depietro.tuiter.data.user.UserDefaultRepository
import ar.com.depietro.tuiter.data.user.UserRepository
import ar.com.depietro.tuiter.data.user.network.TuiterHTTP
import ar.com.depietro.tuiter.data.user.network.TuiterHTTPClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserDefaultRepository): UserRepository

    @Binds
    abstract fun bindTuiterCLient(tuiterHTTPClient: TuiterHTTPClient): TuiterHTTP
}