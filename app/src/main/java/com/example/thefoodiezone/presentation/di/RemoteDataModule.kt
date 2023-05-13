package com.example.thefoodiezone.presentation.di

import com.example.thefoodiezone.data.api.RestaurantsAPIService
import com.example.thefoodiezone.data.repository.dataSource.RestaurantsRemoteDataSource
import com.example.thefoodiezone.data.repository.dataSourceImpl.RestaurantsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(restaurantsAPIService: RestaurantsAPIService): RestaurantsRemoteDataSource{
        return RestaurantsRemoteDataSourceImpl(restaurantsAPIService)
    }
}