package com.example.dogexamplekf.dogs.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.dogexamplekf.R
import com.example.dogexamplekf.databinding.ActivityDogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityDogBinding
    private lateinit var navController: NavController

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, DogActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        onNavComponentCreated()
    }

    private fun initView() {
        _binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

    private fun onNavComponentCreated() {
        navController = this.findNavController(R.id.main_content)
        navController.apply {
            setGraph(navController.navInflater.inflate(R.navigation.nav_dogs), null)
            navigate(R.id.dog_fragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}