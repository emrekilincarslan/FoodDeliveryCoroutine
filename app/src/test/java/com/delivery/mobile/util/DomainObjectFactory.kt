package com.delivery.mobile.util

import com.delivery.mobile.restaurants.data.Restaurant
import com.delivery.mobile.restaurants.data.SortingValues


object DomainObjectFactory {

    fun createRestaurant() = Restaurant(name ="Tandoori Express",favourited = false,status = "closed",sortingValues = SortingValues(bestMatch = 1.0f,
        newest = 266.0f,ratingAverage = 4.5f,distance = 2308,popularity = 123,averageProductPrice = 1146,deliveryCosts = 150,minCost = 1300) )

    fun createRestaurants(count: Int): List<Restaurant> {
        return (0 until count).map {
            createRestaurant()
        }
    }

}
