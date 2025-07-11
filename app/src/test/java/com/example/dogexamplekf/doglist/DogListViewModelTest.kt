package com.example.dogexamplekf.doglist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dogexamplekf.dogs.domain.usecase.GetDogListUseCase
import com.example.dogexamplekf.dogs.domain.usecase.GetDogListUseCaseResult
import com.example.dogexamplekf.dogs.presentation.UIState
import com.example.dogexamplekf.dogs.presentation.doglist.DogListViewModel
import com.example.dogexamplekf.utils.Constants.ERROR_MESSAGE
import com.example.roomlib.entity.DogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class DogListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var getDogListUseCase: GetDogListUseCase

    private lateinit var viewModel: DogListViewModel

    @Mock
    private lateinit var observer: Observer<UIState>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = DogListViewModel(getDogListUseCase)
        viewModel.uiState.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getDogList should post Success when use case returns success`() = runTest {
        // Arrange
        val dogList = listOf(
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
        `when`(getDogListUseCase.execute())
            .thenReturn(GetDogListUseCaseResult.Success(dogList))

        // Act
        viewModel.getDogList()

        // Assert
        verify(observer).onChanged(UIState.Loading)
        verify(observer).onChanged(UIState.DogListSuccess(dogList))
    }

    @Test
    fun `getDogList should post Error when use case returns failure`() = runTest {
        // Arrange
        `when`(getDogListUseCase.execute())
            .thenReturn(GetDogListUseCaseResult.Fail(ERROR_MESSAGE))

        // Act
        viewModel.getDogList()

        // Assert
        verify(observer).onChanged(UIState.Loading)
        verify(observer).onChanged(UIState.Error(ERROR_MESSAGE))
    }
}
