package com.example.thefoodiezone.data.repository.dataSourceImpl

import com.example.thefoodiezone.data.api.RestaurantsAPIService
import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.repository.dataSource.RestaurantsRemoteDataSource
import retrofit2.Response

class RestaurantsRemoteDataSourceImpl(private val restaurantsAPIService: RestaurantsAPIService): RestaurantsRemoteDataSource {

    override suspend fun getLocalRestaurants(location: String, term: String): Response<APIResponse> {
        return restaurantsAPIService.getBusinesses(location, term)
    }

    override suspend fun getRestaurantsWithAddress(location: String): Response<APIResponse> {
        return restaurantsAPIService.getRestaurantsWithAddress(location)
    }

    override suspend fun getRestaurantsWithCuisineType(
        location: String,
        category: String
    ): Response<APIResponse> {
        return restaurantsAPIService.getRestaurantsWithCuisine(location, arrayOf(category))
    }

    override suspend fun sortRestaurants(location: String, sortBy: String): Response<APIResponse> {
        return restaurantsAPIService.sortRestaurants(location, sortBy)
    }
}