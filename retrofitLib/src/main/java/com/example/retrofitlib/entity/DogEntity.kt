package com.example.retrofitlib.entity

import com.google.gson.annotations.SerializedName

data class DogEntity(
    val dogName: String? = "",
    val description: String? = "",
    val age: Int,
    @SerializedName("image")
    val imageUrl: String? = ""
)