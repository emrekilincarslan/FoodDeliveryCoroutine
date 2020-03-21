package com.delivery.mobile.dagger

import androidx.lifecycle.ViewModelProvider
import com.delivery.mobile.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory







}