package ar.com.depietro.tuiter.data.preference

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun bindPreferenceRepository(preferenceRepositoryImpl: LocalSharedPreferenceRepository): PreferenceRepository
}