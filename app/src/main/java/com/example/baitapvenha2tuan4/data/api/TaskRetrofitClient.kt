package com.example.baitapvenha2tuan4.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TaskRetrofitClient {
    private const val BASE_URL = "https://amock.io/api/researchUTH/"

    val instance: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }
}
