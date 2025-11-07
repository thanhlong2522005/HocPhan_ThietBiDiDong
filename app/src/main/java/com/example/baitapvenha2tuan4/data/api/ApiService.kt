package com.example.baitapvenha2tuan4.data.api

import com.example.baitapvenha2tuan4.data.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("v2/product")
    suspend fun getProduct(): Product
}
