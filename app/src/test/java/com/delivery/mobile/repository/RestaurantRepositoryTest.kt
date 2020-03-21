package com.delivery.mobile.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.delivery.mobile.api.RestaurantService
import com.delivery.mobile.data.ApplicationDatabase
import com.delivery.mobile.restaurants.data.RestaurantsDao
import com.delivery.mobile.restaurants.data.RestaurantsRemoteDataSource
import com.delivery.mobile.restaurants.data.RestaurantsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class RestaurantRepositoryTest {
    private lateinit var repository: RestaurantsRepository
    private val dao = mock(RestaurantsDao::class.java)
    private val service = mock(RestaurantService::class.java)
    private val remoteDataSource = RestaurantsRemoteDataSource(service)
    private val mockRemoteDataSource = spy(remoteDataSource)

    private val restaurantName = "Tanoshii Sushi"
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun init() {
        val db = mock(ApplicationDatabase::class.java)
        `when`(db.restaurantsDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = RestaurantsRepository(dao, remoteDataSource)
    }

    @Test
    fun loadRestaurantsFromNetwork() {
        runBlocking {

            verify(dao, never()).getRestaurant(restaurantName)
            verifyZeroInteractions(dao)
        }
    }

}