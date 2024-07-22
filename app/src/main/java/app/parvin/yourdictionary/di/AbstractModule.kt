package app.parvin.yourdictionary.di

import app.parvin.yourdictionary.data.repositories.WordRepositoryImpl
import app.parvin.yourdictionary.domain.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {

    @Binds
    @Singleton
    abstract fun bindWordRepository(impl: WordRepositoryImpl): WordRepository
}