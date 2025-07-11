package com.example.dogexamplekf.dogs.data

import com.example.dogexamplekf.dogs.domain.repository.DogRepository
import com.example.dogexamplekf.utils.Constants.ERROR_MESSAGE
import com.example.dogexamplekf.utils.ServiceResponse
import com.example.dogexamplekf.utils.extensions.toModel
import com.example.retrofitlib.service.DogServiceApi
import com.example.roomlib.dao.DogDataDao
import com.example.roomlib.entity.DogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepositoryImp @Inject constructor(
    private val dogService: DogServiceApi,
    private val dogDataDao: DogDataDao,
) : DogRepository {
    override suspend fun getDogList(): ServiceResponse<List<DogModel>> =
        withContext(Dispatchers.IO) {
            try {
                val response = dogService.getDogsList()
                return@withContext if (response.isSuccessful) {
                    val dogList = response.body()?.toModel() ?: emptyList()
                    dogDataDao.insert(dogList)
                    ServiceResponse.Successful(dogList)
                } else {
                    ServiceResponse.Failed(ERROR_MESSAGE)
                }
            } catch (e: Exception) {
                val dogList = dogDataDao.fetchItems()
                return@withContext if (dogList.isNotEmpty()) {
                    ServiceResponse.Successful(dogList)
                } else {
                    ServiceResponse.Failed(ERROR_MESSAGE)
                }
            }
        }
}