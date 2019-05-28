package com.zaraos.curriculum.di.components

import android.app.Application
import com.zaraos.curriculum.di.modules.BaseAppModule
import com.zaraos.curriculum.di.modules.BaseNetworkModule
import com.zaraos.curriculum.di.scopes.BaseScope
import dagger.Component
import retrofit2.Retrofit

@BaseScope
@Component(modules = [BaseAppModule::class, BaseNetworkModule::class])
interface BaseNetworkComponent {
    val application: Application
    val retrofit: Retrofit
}