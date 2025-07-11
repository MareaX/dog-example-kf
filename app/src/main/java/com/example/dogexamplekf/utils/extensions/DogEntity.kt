package com.example.dogexamplekf.utils.extensions

import com.example.retrofitlib.entity.DogEntity
import com.example.roomlib.entity.DogModel
import kotlin.collections.map

fun List<DogEntity>.toModel(): List<DogModel> {
    return this.map {
        DogModel(
            dogName = it.dogName,
            description = it.description,
            age = it.age,
            imageUrl = it.imageUrl
        )
    }
}