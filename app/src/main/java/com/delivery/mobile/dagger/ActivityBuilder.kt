package com.delivery.mobile.dagger

import com.delivery.mobile.MainActivity
import com.delivery.mobile.dagger.main.MainActivityModule
import com.delivery.mobile.dagger.main.MainFragmentsBuildersModule
import com.delivery.mobile.dagger.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainViewModelsModule::class, MainFragmentsBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}