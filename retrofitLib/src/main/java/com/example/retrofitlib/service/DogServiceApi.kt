package com.example.retrofitlib.service


import com.example.retrofitlib.config.RetrofitApi.Person.Companion.DOGS_URL
import com.example.retrofitlib.entity.DogEntity
import retrofit2.Response
import retrofit2.http.GET

interface DogServiceApi {

    @GET(DOGS_URL)
    suspend fun getDogsList() : Response<List<DogEntity>>
}