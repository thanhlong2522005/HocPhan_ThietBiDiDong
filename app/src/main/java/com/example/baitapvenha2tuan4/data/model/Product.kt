package com.example.baitapvenha2tuan4.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("des")
    val description: String,

    @SerializedName("imgURL")
    val image: String
)
