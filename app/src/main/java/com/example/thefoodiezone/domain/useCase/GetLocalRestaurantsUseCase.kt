package com.example.thefoodiezone.domain.useCase

import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.repository.RestaurantsRepositoryImpl
import com.example.thefoodiezone.data.util.Resource
import com.example.thefoodiezone.domain.repository.RestaurantsRepository

class GetLocalRestaurantsUseCase(private val restaurantsRepository: RestaurantsRepository) {

    suspend fun execute(location: String, term: String): Resource<APIResponse>{
        return restaurantsRepository.getLocalRestaurants(location, term)
    }

    /*suspend fun executeSortBy(location: String, sortBy: String): Resource<APIResponse> {
        return restaurantsRepository.sortRestaurants(location,sortBy)
    }*/
}