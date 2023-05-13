package com.example.thefoodiezone.presentation.di

import com.example.thefoodiezone.BuildConfig
import com.example.thefoodiezone.data.api.RestaurantsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //to get the retrofit instance & APIService dependencies
@InstallIn(SingletonComponent::class) //using dagger hilt we have already created component interface or in-built set of components, all we need is @InstallIn
class NetModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesRestaurantsAPIService(retrofit: Retrofit): RestaurantsAPIService{
        return retrofit.create(RestaurantsAPIService::class.java)
    }
}