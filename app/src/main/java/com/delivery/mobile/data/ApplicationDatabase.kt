package com.delivery.mobile.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delivery.mobile.restaurants.data.Restaurant
import com.delivery.mobile.restaurants.data.RestaurantsDao

@Database(
    entities = [Restaurant::class],
    version = 1,exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun restaurantsDao(): RestaurantsDao


}