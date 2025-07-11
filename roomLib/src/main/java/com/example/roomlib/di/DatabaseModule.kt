package com.example.roomlib.di

import com.example.roomlib.dao.DogDataDao
import com.example.roomlib.database.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Módulo RoomModule que proporciona dependencias relacionadas con Room a nivel Singleton.
 */
@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {
    /**
     * Proporciona una instancia Dao de DogDatabase para obtener los datos guardados.
     */
    @Provides
    @ViewModelScoped
    fun provideDogDao(dogDatabase: DogDatabase): DogDataDao {
        return dogDatabase.dogDatabaseDao()
    }
}