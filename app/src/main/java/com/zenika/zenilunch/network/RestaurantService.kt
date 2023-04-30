package com.zenika.zenilunch.network

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface RestaurantService {
    @GET("main/restaurants.json")
    suspend fun getRestaurants(): List<RestaurantDto>
}

@Module
@InstallIn(SingletonComponent::class)
class RestaurantNetwork @Inject constructor() {

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                Log.d("Network", "URL: ${request.url}")
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    suspend fun getRestaurants(): List<RestaurantDto> {
        val restaurants: RestaurantService by lazy {
            Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/audreygentilizenika/ZeniLunch/")
                .client(createOkHttpClient())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RestaurantService::class.java)
        }
        return restaurants.getRestaurants()
    }
}