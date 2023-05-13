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
}