package com.delivery.mobile.restaurants.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * The Data Access Object for the Restaurant class.
 */
@Dao
interface RestaurantsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(restaurantList: List<Restaurant>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurant : Restaurant)



    @Query("SELECT * FROM restaurants ORDER BY name DESC")
    fun getRestaurants(): LiveData<List<Restaurant>>


     @Query("SELECT * FROM restaurants WHERE name= :name")
    fun getRestaurant(name: String): LiveData<Restaurant>



}
