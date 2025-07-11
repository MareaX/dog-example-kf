package com.example.dogexamplekf.data

import com.example.dogexamplekf.dogs.data.DogRepositoryImp
import com.example.dogexamplekf.utils.Constants.ERROR_MESSAGE
import com.example.dogexamplekf.utils.ServiceResponse
import com.example.retrofitlib.entity.DogEntity
import com.example.retrofitlib.service.DogServiceApi
import com.example.roomlib.dao.DogDataDao
import com.example.roomlib.entity.DogModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class DogRepositoryImpTest {

    @Mock
    private lateinit var dogService: DogServiceApi

    @Mock
    private lateinit var dogDataDao: DogDataDao

    private lateinit var repository: DogRepositoryImp

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = DogRepositoryImp(dogService, dogDataDao)
    }

    @Test
    fun `getDogList returns Successful when service returns data`() = runTest(dispatcher) {
        val dogDtoList = listOf(
            DogEntity(
                dogName = "Rex",
                description = "lol",
                age = 2,
                imageUrl = ""
            )
        )
        val dogModelList = listOf(
            DogModel(
                id = 0,
                dogName = "Rex",
                description = "lol",
                age = 2,
                imageUrl = ""
            )
        )
        val response = Response.success(dogDtoList)

        `when`(dogService.getDogsList()).thenReturn(response)

        val result = repository.getDogList()

        assert(result is ServiceResponse.Successful)
        assertEquals(dogModelList, (result as ServiceResponse.Successful).content)
        verify(dogDataDao).insert(dogModelList)
    }

    @Test
    fun `getDogList returns Successful from cache when service throws and cache not empty`() =
        runTest(dispatcher) {
            // Arrange
            `when`(dogService.getDogsList()).thenThrow(RuntimeException(ERROR_MESSAGE))
            val cachedList = listOf(
                DogModel(
                    id = 0,
                    dogName = "Fido",
                    description = "xd",
                    age = 2,
                    imageUrl = ""
                ),
                DogModel(
                    id = 0,
                    dogName = "Rex",
                    description = "lol",
                    age = 2,
                    imageUrl = ""
                )
            )
            `when`(dogDataDao.fetchItems()).thenReturn(cachedList)

            val result = repository.getDogList()

            assert(result is ServiceResponse.Successful)
            assertEquals(cachedList, (result as ServiceResponse.Successful).content)
        }

    @Test
    fun `getDogList returns Failed when service throws and cache is empty`() = runTest(dispatcher) {
        `when`(dogService.getDogsList()).thenThrow(RuntimeException(ERROR_MESSAGE))
        `when`(dogDataDao.fetchItems()).thenReturn(emptyList())

        val result = repository.getDogList()

        assert(result is ServiceResponse.Failed)
        assertEquals(ERROR_MESSAGE, (result as ServiceResponse.Failed).errorMessage)
    }
}