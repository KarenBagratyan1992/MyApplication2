package com.example.myapplication

import retrofit2.http.GET

interface UserApi {
    @GET("public/v2/users")
    suspend fun getApi():List<UserResponseModel>
}