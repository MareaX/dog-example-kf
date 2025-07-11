package com.example.roomlib.di

import android.content.Context
import androidx.room.Room
import com.example.roomlib.database.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo RoomModule que proporciona dependencias relacionadas con Room a nivel Singleton.
 */
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private lateinit var databaseName: String

    /**
     * Proporciona una instancia de la base de datos.
     * @return devuelve una instancia de PersonDatabase creada a partir del contexto de la aplicación y el nombre de la base de datos.
     */
    @Provides
    @Singleton
    fun provideDogDatabase(@ApplicationContext appContext: Context): DogDatabase {
        return Room.databaseBuilder(
            appContext,
            DogDatabase::class.java,
            databaseName
        ).build()
    }

    fun init(databaseName: String) {
        RoomModule.databaseName = databaseName
    }
}