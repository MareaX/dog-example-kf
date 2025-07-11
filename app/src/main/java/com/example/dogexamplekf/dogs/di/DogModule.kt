package com.example.dogexamplekf.dogs.di

import com.example.dogexamplekf.dogs.data.DogRepositoryImp
import com.example.dogexamplekf.dogs.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DogModule {
    @Binds
    abstract fun bindDogRepository(
        dogRepositoryImp: DogRepositoryImp
    ): DogRepository
}