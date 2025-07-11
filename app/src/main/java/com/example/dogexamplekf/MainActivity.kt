package com.example.dogexamplekf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dogexamplekf.dogs.presentation.DogActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DogActivity.startActivity(this)
    }
}