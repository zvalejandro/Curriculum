package com.zaraos.curriculum.domain.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
data class UserEntity(
    @SerializedName("uid") val uid: String = "",
    @SerializedName("username") val username: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("name") val name: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("current_position") val currentPosition: String,
    @SerializedName("email") val email: String,
    @SerializedName("linkedin_id") val linkedinId: String,
    @SerializedName("github_id") val githubId: String,
    @SerializedName("experience") val experience: Experience?
) {

    inner class Experience(

        @SerializedName("uid")
        val uid: String = "",

        @SerializedName("company_name")
        val companyName: String = "",

        @SerializedName("role_name")
        val roleName: String = ""
    )
}