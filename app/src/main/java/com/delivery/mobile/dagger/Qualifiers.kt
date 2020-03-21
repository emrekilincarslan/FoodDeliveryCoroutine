package com.delivery.mobile.dagger

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RestaurantApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeIO
