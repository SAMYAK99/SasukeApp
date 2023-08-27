package com.example.sasukeapp.di

import android.content.Context
import com.example.sasukeapp.data.repository.DataStoreOperationsImpl
import com.example.sasukeapp.data.repository.Repository
import com.example.sasukeapp.domain.repository.DataStoreOperations
import com.example.sasukeapp.domain.usecases.GetAllHeroesUseCase
import com.example.sasukeapp.domain.usecases.GetSelectedHeroUseCase
import com.example.sasukeapp.domain.usecases.ReadOnBoardingUseCase
import com.example.sasukeapp.domain.usecases.SaveOnBoardingUseCase
import com.example.sasukeapp.domain.usecases.SearchHeroesUseCase
import com.example.sasukeapp.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Singleton
    @Provides
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun providesUseCases(repository: Repository) : UseCases {
        return  UseCases(
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository) ,
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository) ,
            getAllHeroesUseCase = GetAllHeroesUseCase(repository) ,
            searchHeroesUseCase = SearchHeroesUseCase(repository) ,
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository)
        )
    }


}