package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://gorest.co.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}