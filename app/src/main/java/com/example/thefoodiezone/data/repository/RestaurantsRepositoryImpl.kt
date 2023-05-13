package com.example.thefoodiezone.data.repository

import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.repository.dataSource.RestaurantsRemoteDataSource
import com.example.thefoodiezone.data.util.Resource
import com.example.thefoodiezone.domain.repository.RestaurantsRepository
import dagger.Provides
import retrofit2.Response
import javax.inject.Inject


class RestaurantsRepositoryImpl(private val restaurantsRemoteDataSource: RestaurantsRemoteDataSource): RestaurantsRepository {

    override suspend fun getLocalRestaurants(location: String, term: String): Resource<APIResponse> {
        return responseToResource(restaurantsRemoteDataSource.getLocalRestaurants(location, term))
    }

    private fun responseToResource(response: Response<APIResponse>) : Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun searchLocalRestaurants(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }
}