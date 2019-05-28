package com.zaraos.curriculum

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    init {
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()
    }
}