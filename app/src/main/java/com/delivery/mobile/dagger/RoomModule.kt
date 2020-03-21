package com.delivery.mobile.dagger

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.delivery.mobile.api.RestaurantService
import com.delivery.mobile.data.ApplicationDatabase
import com.delivery.mobile.restaurants.data.RestaurantsDao
import com.delivery.mobile.restaurants.data.RestaurantsRemoteDataSource
import dagger.Module
import dagger.Provides
import fr.speekha.httpmocker.MockResponseInterceptor
import fr.speekha.httpmocker.gson.GsonMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RoomModule {


    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Singleton
    @Provides
    fun provideRestaurantService(okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okHttpClient, GsonConverterFactory.create() ,RestaurantService::class.java)



    @Singleton
    @Provides
    fun provideRestaurantsRemoteDataSource(restaurantService: RestaurantService)
            = RestaurantsRemoteDataSource(restaurantService)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideSimpleMockInterceptor(application: Application) =
        MockResponseInterceptor.Builder()
            .parseScenariosWith(GsonMapper())
            .loadFileWith { application.assets.open(it) }
            .setInterceptorStatus(MockResponseInterceptor.Mode.ENABLED)
            .addFakeNetworkDelay(500L)
            .build()

    @Provides
    fun provideOkHttpClient(interceptor: MockResponseInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()


    @RestaurantApi
    @Provides
    fun providePrivateOkHttpClient(interceptor: MockResponseInterceptor,
        okHttpClient: OkHttpClient
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(interceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): ApplicationDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ApplicationDatabase::class.java, "restaurants.db"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesRestaurantsDao(applicationDatabase: ApplicationDatabase): RestaurantsDao {
        return applicationDatabase.restaurantsDao()
    }

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    private fun createRetrofit(okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RestaurantService.ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private fun <T> provideService(okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        clazz: Class<T>
    ): T {
        return createRetrofit(okHttpClient,converterFactory).create(clazz)
    }
//    .client(OkHttpClient.Builder()
//    .addInterceptor(SimpleMockInterceptor(true))
//    .build())

}