package com.example.retrofitlib.di

import com.example.retrofitlib.service.DogServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {
    @Provides
    @ViewModelScoped
    fun providesDogApi(retrofit: Retrofit): DogServiceApi = retrofit.create(
        DogServiceApi::class.java
    )
}