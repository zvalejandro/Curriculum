package com.zaraos.curriculum

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.zaraos.curriculum.di.components.BaseNetworkComponent
import com.zaraos.curriculum.di.components.DaggerBaseNetworkComponent
import com.zaraos.curriculum.di.modules.BaseAppModule

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class MyApplication : Application() {

    protected lateinit var baseDaggerNetworkComponent: BaseNetworkComponent

    companion object {
        lateinit var baseNetworkComponent: BaseNetworkComponent
        lateinit var instance: MyApplication
    }

    init {
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()

        baseDaggerNetworkComponent =
            DaggerBaseNetworkComponent.builder()
                .baseAppModule(BaseAppModule(this))
                .build()
        baseNetworkComponent = baseDaggerNetworkComponent
    }
}