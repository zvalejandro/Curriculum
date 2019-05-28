package com.zaraos.curriculum.di.modules


import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zaraos.curriculum.BuildConfig
import com.zaraos.curriculum.di.scopes.BaseScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
open class BaseNetworkModule {

    companion object {
        const val HTTP_CONNECT_TIMEOUT = 60 * 1000L
        const val HTTP_READ_TIMEOUT = 60 * 1000L
        const val HTTP_CALL_TIMEOUT = 60 * 1000L
    }

    @Provides
    @BaseScope
    fun providesSharedPreferences(application: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @BaseScope
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @BaseScope
    fun provideGson(): Gson = with(GsonBuilder()) {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        create()
    }

    @Provides
    @BaseScope
    fun provideOkhttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor): OkHttpClient = with(OkHttpClient.Builder()) {

        connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
        //callTimeout(HTTP_CALL_TIMEOUT, TimeUnit.MILLISECONDS)
        addInterceptor(loggingInterceptor)
        build()
    }

    @Provides
    @BaseScope
    open fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addCallAdapterFactory(LiveDataCallAdapterFactory())
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build()
    }


    @Provides
    @BaseScope
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }



}
