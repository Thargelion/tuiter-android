package ar.com.depietro.tuiter.data.user.di

import ar.com.depietro.tuiter.data.user.repository.UserDefaultRepository
import ar.com.depietro.tuiter.data.user.repository.UserRepository
import ar.com.depietro.tuiter.data.user.network.UserHTTP
import ar.com.depietro.tuiter.data.user.network.UserHTTPClient
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
    abstract fun bindUserHTTPClient(tuiterHTTPClient: UserHTTPClient): UserHTTP
}