package com.example.thefoodiezone.data.repository.dataSourceImpl

import com.example.thefoodiezone.data.api.RestaurantsAPIService
import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.repository.dataSource.RestaurantsRemoteDataSource
import retrofit2.Response

class RestaurantsRemoteDataSourceImpl(private val restaurantsAPIService: RestaurantsAPIService): RestaurantsRemoteDataSource {

    override suspend fun getLocalRestaurants(location: String, term: String): Response<APIResponse> {
        return restaurantsAPIService.getBusinesses(location, term)
    }
}