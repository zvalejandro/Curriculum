package com.zaraos.curriculum.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
data class ExperienceEntity(
    @SerializedName("uid") val uid: String = "",
    @SerializedName("company_name") val companyName: String,
    @SerializedName("company_logo") val companyLogo: String,
    @SerializedName("role_name") val roleName: String
)