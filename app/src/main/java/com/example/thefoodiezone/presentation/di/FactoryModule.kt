package com.example.thefoodiezone.presentation.di

import android.app.Application
import com.example.thefoodiezone.domain.useCase.GetLocalRestaurantsUseCase
import com.example.thefoodiezone.presentation.viewModel.RestaurantsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesRestaurantsViewModelFactory(
        application: Application, getLocalRestaurantsUseCase: GetLocalRestaurantsUseCase
    ): RestaurantsViewModelFactory{

        return RestaurantsViewModelFactory(application, getLocalRestaurantsUseCase)
    }
}