package com.zaraos.curriculum.data.webservice

import com.zaraos.curriculum.domain.entity.BaseResponse
import com.zaraos.curriculum.domain.entity.ExperienceEntity
import com.zaraos.curriculum.domain.entity.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("feed/{username}")
    fun getUserInformation(@Path("username") username: String): Observable<BaseResponse<UserEntity>>

    @GET("cv/experience/{username}")
    fun getExperience(@Path("username") username: String): Observable<BaseResponse<List<ExperienceEntity>>>

}