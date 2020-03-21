package com.delivery.mobile.restaurants.data

import com.delivery.mobile.api.BaseDataSource
import com.delivery.mobile.api.RestaurantService
import javax.inject.Inject

/**
 * Works with the Restaurants API to get data.
 */
class RestaurantsRemoteDataSource @Inject constructor(private val service: RestaurantService) : BaseDataSource() {

    suspend fun fetchData() = getResult() { service.getRestaurantsAsync().await() }
}
