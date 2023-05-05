package com.zenika.zenilunch.network

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.zenika.zenilunch.agency.model.Agency
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

interface RestaurantService {
    @GET("{file}")
    suspend fun getRestaurants(@Path("file") file: String): List<RestaurantDto>
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
    suspend fun getRestaurants(agency: Agency): List<RestaurantDto> {
        val restaurants: RestaurantService by lazy {
            Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Zenika/ZeniLunch/config/")
                .client(createOkHttpClient())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RestaurantService::class.java)
        }
        return restaurants.getRestaurants(agency.restaurantsUrlPath)
    }
}
