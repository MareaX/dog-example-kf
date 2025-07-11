package com.example.dogexamplekf.dogs.domain.usecase

import com.example.dogexamplekf.dogs.domain.repository.DogRepository
import com.example.dogexamplekf.utils.ServiceResponse
import com.example.roomlib.entity.DogModel
import javax.inject.Inject

class GetDogListUseCase @Inject constructor(
    private val amortizationRepository: DogRepository
) {
    suspend fun execute(): GetDogListUseCaseResult {
        return when (val result = amortizationRepository.getDogList()) {
            is ServiceResponse.Successful -> GetDogListUseCaseResult.Success(result.content)
            is ServiceResponse.Failed -> GetDogListUseCaseResult.Fail(result.errorMessage)
        }
    }
}

sealed class GetDogListUseCaseResult {
    data class Success(val dogList: List<DogModel>) : GetDogListUseCaseResult()
    data class Fail(val message: String) : GetDogListUseCaseResult()
}