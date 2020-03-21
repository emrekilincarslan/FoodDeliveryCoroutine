package com.delivery.mobile.restaurants.data

import androidx.lifecycle.LiveData
import com.delivery.mobile.data.Result
import com.delivery.mobile.data.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class RestaurantsRepository @Inject constructor(
    private val dao: RestaurantsDao,
    private val remoteSource: RestaurantsRemoteDataSource
) {
    val restaurants: LiveData<Result<List<Restaurant>>> =
        resultLiveData(
            databaseQuery = { dao.getRestaurants() },
            networkCall = { remoteSource.fetchData() },
            saveCallResult = { dao.insertAll(it) }
        )
    fun insertRestaurant(restaurant: Restaurant) {
        dao.insertRestaurant(restaurant)
    }


}
