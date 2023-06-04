package ar.com.depietro.tuiter.data.tuit.di

import ar.com.depietro.tuiter.data.tuit.network.TuitHTTP
import ar.com.depietro.tuiter.data.tuit.network.TuitHTTPClient
import ar.com.depietro.tuiter.data.tuit.repository.TuitDefaultRepository
import ar.com.depietro.tuiter.data.tuit.repository.TuitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TuitDataModule {
    @Binds
    abstract fun bindTuitRepository(tuitRepositoryImpl: TuitDefaultRepository): TuitRepository

    @Binds
    abstract fun bindTuitClient(tuitHTTPClient: TuitHTTPClient): TuitHTTP
}