package com.delivery.mobile.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class RestaurantServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: RestaurantService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RestaurantService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestRestaurants() {
        runBlocking {
            enqueueResponse("restaurants.json")
            val resultResponse = service.getRestaurantsTESTAsyn().body()

            val request = mockWebServer.takeRequest()
            assertNotNull(resultResponse)
            assertThat(request.path, `is`("/restaurants"))
        }
    }

    @Test
    fun getRestaurantsResponse() {
        runBlocking {
            enqueueResponse("restaurants.json")
            val listRestaurant_ = service.getRestaurantsTESTAsyn().body()

            assertThat(listRestaurant_?.size, `is`(19))
        }
    }




    @Test
    fun getRestaurantItem() {
        runBlocking {
            enqueueResponse("restaurants.json")
            val resultResponse = service.getRestaurantsTESTAsyn().body()

            val restaurant = resultResponse?.get(0)
            assertThat(restaurant?.name, `is`("Tanoshii Sushi"))
            assertThat(restaurant?.status, `is`("open"))
            assertThat(restaurant?.sortingValues?.bestMatch, `is`(0.0f))
            assertThat(restaurant?.sortingValues?.newest, `is`(96.0f))
            assertThat(restaurant?.sortingValues?.ratingAverage, `is`(4.5f))
            assertThat(restaurant?.sortingValues?.distance, `is`(1190))
            assertThat(restaurant?.sortingValues?.popularity, `is`(17))
            assertThat(restaurant?.sortingValues?.averageProductPrice, `is`(1536))
            assertThat(restaurant?.sortingValues?.deliveryCosts, `is`(200))
            assertThat(restaurant?.sortingValues?.minCost, `is`(200))
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                ?.getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
                source.readString(Charsets.UTF_8))
        )
    }
}
