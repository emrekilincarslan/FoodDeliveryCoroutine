package com.delivery.mobile
import com.delivery.mobile.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class BaseApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

       return DaggerAppComponent.builder().application(this).build()

    }
}
