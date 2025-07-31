package br.com.waldirbaia.buscausuario.di

import br.com.waldirbaia.buscausuario.data.local.interfac.HistoryRepository
import br.com.waldirbaia.buscausuario.domain.repository.HistoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHistoryRepository(
        impl: HistoryRepositoryImpl
    ): HistoryRepository
}
