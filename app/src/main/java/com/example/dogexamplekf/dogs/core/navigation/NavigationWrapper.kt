package com.example.dogexamplekf.dogs.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogexamplekf.dogs.presentation.doglist.DogListScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DogList) {

        composable<DogList> {
            DogListScreen()
        }

    }
}