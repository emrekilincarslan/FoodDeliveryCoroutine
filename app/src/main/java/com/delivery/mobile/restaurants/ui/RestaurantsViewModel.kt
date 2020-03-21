package com.delivery.mobile.restaurants.ui

import androidx.lifecycle.ViewModel
import com.delivery.mobile.restaurants.data.Restaurant
import com.delivery.mobile.restaurants.data.RestaurantsRepository
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(val repository: RestaurantsRepository) : ViewModel() {
    val restaurants=repository.restaurants
    fun insertRestaurant(restaurant: Restaurant) {
        repository.insertRestaurant(restaurant)
    }
}

