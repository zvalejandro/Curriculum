package com.zaraos.curriculum.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
data class UserEntity(
    @SerializedName("username") val username: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("name") val name: String,
    @SerializedName("last_name") val last_name: String
)