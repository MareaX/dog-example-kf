package com.example.dogexamplekf.usecase

import com.example.dogexamplekf.dogs.domain.repository.DogRepository
import com.example.dogexamplekf.dogs.domain.usecase.GetDogListUseCase
import com.example.dogexamplekf.dogs.domain.usecase.GetDogListUseCaseResult
import com.example.dogexamplekf.utils.Constants.ERROR_MESSAGE
import com.example.dogexamplekf.utils.ServiceResponse
import com.example.roomlib.entity.DogModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetDogListUseCaseTest {

    @Mock
    private lateinit var dogRepository: DogRepository

    private lateinit var getDogListUseCase: GetDogListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getDogListUseCase = GetDogListUseCase(dogRepository)
    }

    @Test
    fun `execute returns Success when repository returns successful response`() = runTest {
        val expectedList = listOf(
            DogModel(
                id = 0,
                dogName = "Fido",
                description = "xd",
                age = 2,
                imageUrl = ""
            ),
            DogModel(id = 0,
                dogName = "Rex",
                description = "lol",
                age = 2,
                imageUrl = "")
        )

        `when`(dogRepository.getDogList()).thenReturn(ServiceResponse.Successful(expectedList))

        val result = getDogListUseCase.execute()
        assert(result is GetDogListUseCaseResult.Success)
        assertEquals(expectedList, (result as GetDogListUseCaseResult.Success).dogList)
    }

    @Test
    fun `execute returns Fail when repository returns failed response`() = runTest {
        `when`(dogRepository.getDogList()).thenReturn(ServiceResponse.Failed(ERROR_MESSAGE))

        val result = getDogListUseCase.execute()

        assert(result is GetDogListUseCaseResult.Fail)
        assertEquals(ERROR_MESSAGE, (result as GetDogListUseCaseResult.Fail).message)
    }
}
