package com.zaraos.curriculum.di.components

import com.zaraos.curriculum.data.webservice.ApiService
import com.zaraos.curriculum.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AppComponent {

    @Provides
    @AppScope
    fun provideRetrofitClient(retrofit: Retrofit) = retrofit.create(ApiService::class.java)!!

}