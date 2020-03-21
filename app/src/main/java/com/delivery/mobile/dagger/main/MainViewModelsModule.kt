package com.delivery.mobile.dagger.main

import androidx.lifecycle.ViewModel
import com.delivery.mobile.restaurants.ui.RestaurantsViewModel
import com.delivery.mobile.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsViewModel::class)
    internal abstract fun bindRestaurantsViewModel(viewModel: RestaurantsViewModel): ViewModel



}