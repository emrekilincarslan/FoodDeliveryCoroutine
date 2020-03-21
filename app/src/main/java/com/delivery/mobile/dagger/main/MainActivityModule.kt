package com.delivery.mobile.dagger.main

import androidx.fragment.app.FragmentActivity
import com.delivery.mobile.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {
    @Binds
    abstract fun provideMainActivity(activity: MainActivity): FragmentActivity
}