package com.zaraos.curriculum.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
data class BaseResponse<T>(
    @SerializedName("code") var code: Int,
    @SerializedName("status") var status: String = "",
    @SerializedName("data") var data: T?
)