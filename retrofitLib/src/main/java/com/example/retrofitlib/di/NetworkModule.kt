package com.example.retrofitlib.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT = 30L
    private lateinit var baseUrl: String

    /**
     * Proporciona una instancia de OkHttpClient a nivel Singleton.
     */
    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        connectTimeout(TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
        readTimeout(TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
    }.build()

    /**
     * Proporciona una instancia de Retrofit a nivel Singleton.
     * */
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("$baseUrl/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun init(baseUrl: String) {
        NetworkModule.baseUrl = baseUrl
    }
}