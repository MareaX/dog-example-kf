package com.example.dogexamplekf.dogs.presentation

import com.example.roomlib.entity.DogModel

sealed class UIState {
    data object Loading : UIState()
    data class Error(val errorMessage: String) : UIState()
    data class DogListSuccess(
        val dogList: List<DogModel>
    ) : UIState()
}
