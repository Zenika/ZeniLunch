package com.zenika.zenilunch.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    private val databaseName = "restaurant.db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ): RestaurantDatabase {
        return Room.databaseBuilder(
            context,
            RestaurantDatabase::class.java,
            databaseName
        )
            .build()
    }

    @Provides
    fun provideDao(restaurantDatabase: RestaurantDatabase): RestaurantDao {
        return restaurantDatabase.restaurantDao
    }
}
