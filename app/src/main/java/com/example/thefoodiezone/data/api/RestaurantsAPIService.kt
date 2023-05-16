package com.example.thefoodiezone.data.api

import com.example.thefoodiezone.BuildConfig
import com.example.thefoodiezone.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantsAPIService {

    @Headers("Authorization:Bearer ${BuildConfig.API_KEY}")
    @GET("businesses/search")
    suspend fun getBusinesses(
        @Query("location")
        location: String,
        @Query("term")
        term: String
    ) : Response<APIResponse>

    @Headers("Authorization:Bearer ${BuildConfig.API_KEY}")
    @GET("businesses/search")
    suspend fun getRestaurantsWithAddress(
        @Query("location")
        location: String
    ) : Response<APIResponse>


    @Headers("Authorization:Bearer ${BuildConfig.API_KEY}")
    @GET("businesses/search")
    suspend fun getRestaurantsWithCuisine(
        @Query("location")
        location: String,
        @Query("categories")
        category: Array<String>
    ) : Response<APIResponse>

    @Headers("Authorization:Bearer ${BuildConfig.API_KEY}")
    @GET("businesses/search")
    suspend fun sortRestaurants(
        @Query("location")
        location: String,
        @Query("sort_by")
        sortBy: String
    ) : Response<APIResponse>

}