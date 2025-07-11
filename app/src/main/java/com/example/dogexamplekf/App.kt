package com.example.dogexamplekf

import android.app.Application
import com.example.dogexamplekf.utils.Constants.BASE_URL
import com.example.dogexamplekf.utils.Constants.DATABASE_NAME
import com.example.retrofitlib.di.NetworkModule
import com.example.roomlib.di.RoomModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkModule.init(BASE_URL)
        RoomModule.init(DATABASE_NAME)
    }
}