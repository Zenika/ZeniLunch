package com.zenika.zenilunch.di

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.zenika.zenilunch.data.network.RestaurantGitHubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRestaurantService(httpClient: OkHttpClient): RestaurantGitHubApi {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Zenika/ZeniLunch/config/")
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RestaurantGitHubApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext
        context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                Log.d("Network", "URL: ${request.url}")
                chain.proceed(request)
            }
            .cache(Cache(context.cacheDir, maxSize = 5_000_000))
            .build()
    }
}
