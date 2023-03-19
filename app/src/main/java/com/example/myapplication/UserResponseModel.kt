package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("name")
    val name: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("status")
    val status: String?
)