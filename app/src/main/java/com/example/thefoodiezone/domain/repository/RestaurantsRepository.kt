package com.example.thefoodiezone.domain.repository

import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.util.Resource

interface RestaurantsRepository {

    //These two functions will be used for network communication
    suspend fun getLocalRestaurants(location: String, term: String): Resource<APIResponse>

    suspend fun searchLocalRestaurants(searchQuery: String): Resource<APIResponse>
}