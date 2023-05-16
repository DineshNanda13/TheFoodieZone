package com.example.thefoodiezone.domain.useCase

import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.util.Resource
import com.example.thefoodiezone.domain.repository.RestaurantsRepository

class SearchLocalRestaurantsUseCase(private val restaurantsRepository: RestaurantsRepository) {

    suspend fun execute(location: String, term: String): Resource<APIResponse>{
        return restaurantsRepository.searchLocalRestaurants(location, term)
    }
    suspend fun executeSearchWithAddress(location: String): Resource<APIResponse>{
        return restaurantsRepository.searchWithAddress(location)
    }
    suspend fun executeSearchWithCuisineType(location: String, type: String): Resource<APIResponse> {
        return restaurantsRepository.searchWithCuisineType(location, type)
    }
    suspend fun executeSortBy(location: String, sortBy: String): Resource<APIResponse> {
        return restaurantsRepository.sortRestaurants(location,sortBy)
    }

}