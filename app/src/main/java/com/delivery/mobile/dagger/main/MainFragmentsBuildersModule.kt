package com.delivery.mobile.dagger.main

import com.delivery.mobile.restaurants.ui.RestaurantsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBuildersModule {
    @ContributesAndroidInjector
    abstract fun provideRestaurantsFragment(): RestaurantsFragment

}