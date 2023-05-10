package com.example.thefoodiezone.domain.useCase

import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.util.Resource
import com.example.thefoodiezone.domain.repository.RestaurantsRepository

class SearchLocalRestaurantsUseCase(private val restaurantsRepository: RestaurantsRepository) {

    suspend fun execute(searchQuery: String): Resource<APIResponse>{
        return restaurantsRepository.searchLocalRestaurants(searchQuery)
    }
}