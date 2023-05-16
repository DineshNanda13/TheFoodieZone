package com.example.thefoodiezone.presentation.di

import com.example.thefoodiezone.data.repository.RestaurantsRepositoryImpl
import com.example.thefoodiezone.domain.repository.RestaurantsRepository
import com.example.thefoodiezone.domain.useCase.GetLocalRestaurantsUseCase
import com.example.thefoodiezone.domain.useCase.SearchLocalRestaurantsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetLocalRestaurantsUseCase(restaurantsRepository: RestaurantsRepository): GetLocalRestaurantsUseCase{
        return GetLocalRestaurantsUseCase(restaurantsRepository)
    }

    @Singleton
    @Provides
    fun providesSearchLocalRestaurantsUseCase(restaurantsRepository: RestaurantsRepository): SearchLocalRestaurantsUseCase{
        return SearchLocalRestaurantsUseCase(restaurantsRepository)
    }

}