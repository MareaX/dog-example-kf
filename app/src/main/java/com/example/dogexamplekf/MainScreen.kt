package com.example.dogexamplekf

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.dogexamplekf.ui.theme.DogComposeTheme

@Composable
fun MainScreen(content: @Composable () -> Unit) {
    DogComposeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}