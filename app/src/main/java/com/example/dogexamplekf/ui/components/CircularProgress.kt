package com.example.dogexamplekf.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dogexamplekf.ui.theme.Green
import com.example.dogexamplekf.ui.theme.Red

@Composable
fun CircularProgress(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = Red,
        trackColor = Green,
    )
}