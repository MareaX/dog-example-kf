package com.example.dogexamplekf.dogs.domain.repository

import com.example.dogexamplekf.utils.ServiceResponse
import com.example.roomlib.entity.DogModel

interface  DogRepository {
    suspend fun getDogList(): ServiceResponse<List<DogModel>>

}