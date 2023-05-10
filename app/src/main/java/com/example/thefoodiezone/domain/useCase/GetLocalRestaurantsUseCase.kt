package com.example.thefoodiezone.domain.useCase

import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.util.Resource
import com.example.thefoodiezone.domain.repository.RestaurantsRepository

class GetLocalRestaurantsUseCase(private val restaurantsRepository: RestaurantsRepository) {

    suspend fun execute(): Resource<APIResponse>{
        return restaurantsRepository.getLocalRestaurants()
    }
}