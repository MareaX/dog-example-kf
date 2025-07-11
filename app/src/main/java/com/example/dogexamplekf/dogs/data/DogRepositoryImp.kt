package com.example.dogexamplekf.dogs.data

import com.example.dogexamplekf.dogs.domain.repository.DogRepository
import com.example.dogexamplekf.utils.ServiceResponse
import com.example.roomlib.entity.DogModel

class DogRepositoryImp : DogRepository {
    override suspend fun getDogList(): ServiceResponse<List<DogModel>> {
        TODO("Not yet implemented")
    }
}