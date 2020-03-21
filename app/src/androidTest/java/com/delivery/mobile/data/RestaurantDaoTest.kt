package com.delivery.mobile.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.delivery.mobile.restaurants.data.RestaurantsDao
import com.delivery.mobile.util.getValue
import com.delivery.mobile.util.testRestaurantA
import com.delivery.mobile.util.testRestaurantB
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantDaoTest : DbTest() {
    private lateinit var restaurantsDao: RestaurantsDao
    private val restA = testRestaurantA.copy()
    private val restB = testRestaurantB.copy()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() {
        restaurantsDao = db.restaurantsDao()

        // Insert restaurants in non-alphabetical order to test that results are sorted by name
        runBlocking {
            restaurantsDao.insertAll(listOf(restA, restB))
        }
    }

    @Test fun testGetRestaurants() {
        val list = getValue(restaurantsDao.getRestaurants())
        assertThat(list.size, equalTo(2))

        // Ensure restaurants list is sorted by name
        assertThat(list[0], equalTo(restA))
        assertThat(list[1], equalTo(restB))
    }

    @Test fun testGetRestaurant() {
        assertThat(getValue(restaurantsDao.getRestaurant(restA.name)), equalTo(restA))
    }
}