package com.example.thefoodiezone.data.repository.dataSource

import com.example.thefoodiezone.data.model.APIResponse
import retrofit2.Response

interface RestaurantsRemoteDataSource {

    suspend fun getLocalRestaurants(location: String, term: String): Response<APIResponse>
}