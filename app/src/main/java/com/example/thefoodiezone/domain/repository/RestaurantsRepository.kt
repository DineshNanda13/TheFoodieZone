package com.example.thefoodiezone.domain.repository

import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.util.Resource
import javax.inject.Inject

interface RestaurantsRepository {

    //These functions will be used for network communication
    suspend fun getLocalRestaurants(location: String, term: String): Resource<APIResponse>

    suspend fun searchWithAddress(address: String): Resource<APIResponse>

    suspend fun searchWithCuisineType(location: String, category: String): Resource<APIResponse>

    suspend fun searchLocalRestaurants(location: String, term: String): Resource<APIResponse>

    suspend fun sortRestaurants(location: String, sortBy: String): Resource<APIResponse>
}