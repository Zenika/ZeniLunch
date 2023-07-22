package com.zenika.zenilunch.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantGitHubApi {
    @GET("{file}")
    suspend fun getRestaurants(@Path("file") file: String): List<RestaurantDto>
}
