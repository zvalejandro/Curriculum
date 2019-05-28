package com.zaraos.curriculum.di.modules

import android.app.Application
import com.zaraos.curriculum.di.scopes.BaseScope

import dagger.Module
import dagger.Provides

@Module
open class BaseAppModule(private var mApplication: Application) {

    @Provides
    @BaseScope
    open fun provideApplication() = mApplication
}