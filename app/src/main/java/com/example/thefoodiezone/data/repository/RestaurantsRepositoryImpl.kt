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

    override suspend fun searchWithAddress(address: String): Resource<APIResponse> {
        return  responseToResource(restaurantsRemoteDataSource.getRestaurantsWithAddress(address))
    }

    override suspend fun searchWithCuisineType(
        location: String,
        category: String
    ): Resource<APIResponse> {
        return responseToResource(restaurantsRemoteDataSource.getRestaurantsWithCuisineType(location, category))
    }


    private fun responseToResource(response: Response<APIResponse>) : Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun searchLocalRestaurants(
        location: String,
        term: String
    ): Resource<APIResponse> {
        return responseToResource(restaurantsRemoteDataSource.getLocalRestaurants(location, term))
    }

    override suspend fun sortRestaurants(location: String, sortBy: String): Resource<APIResponse> {
        return responseToResource(restaurantsRemoteDataSource.sortRestaurants(location, sortBy))
    }
}