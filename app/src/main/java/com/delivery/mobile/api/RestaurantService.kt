package com.delivery.mobile.api

import com.delivery.mobile.restaurants.data.Restaurant
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * Restaurants API access points
 */
interface RestaurantService {
    companion object {
        const val ENDPOINT = "https://api.github.com"
    }

    @GET("/fakeurl")
    fun getRestaurantsAsync(): Deferred<Response<List<Restaurant>>>

    // TEST PURPOSE
@GET("/fakeurl")
    fun getRestaurantsTESTAsyn(): Response<List<Restaurant>>


}
