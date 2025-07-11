package com.example.dogexamplekf.dogs.presentation.doglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogexamplekf.dogs.domain.usecase.GetDogListUseCase
import com.example.dogexamplekf.dogs.domain.usecase.GetDogListUseCaseResult
import com.example.dogexamplekf.dogs.presentation.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogListUseCase: GetDogListUseCase
) : ViewModel() {
    private val mutableUiState: MutableLiveData<UIState> = MutableLiveData()
    val uiState: LiveData<UIState> get() = mutableUiState

    fun getDogList() {
        viewModelScope.launch {
            mutableUiState.postValue(UIState.Loading)
            when (val result = getDogListUseCase.execute()) {
                is GetDogListUseCaseResult.Success -> {
                    mutableUiState.postValue(UIState.DogListSuccess(result.dogList))
                }

                is GetDogListUseCaseResult.Fail -> {
                    mutableUiState.postValue(UIState.Error(result.message))
                }
            }
        }
    }
}