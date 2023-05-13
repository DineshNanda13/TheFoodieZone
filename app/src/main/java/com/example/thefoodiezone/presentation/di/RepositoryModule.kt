package com.example.thefoodiezone.presentation.di

import com.example.thefoodiezone.data.repository.RestaurantsRepositoryImpl
import com.example.thefoodiezone.data.repository.dataSource.RestaurantsRemoteDataSource
import com.example.thefoodiezone.domain.repository.RestaurantsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesRestaurantRepositoryInstance(restaurantsRemoteDataSource: RestaurantsRemoteDataSource): RestaurantsRepository{
        return RestaurantsRepositoryImpl(restaurantsRemoteDataSource)
    }
}