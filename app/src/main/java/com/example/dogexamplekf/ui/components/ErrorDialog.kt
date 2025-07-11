package com.example.dogexamplekf.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorDialog(modifier: Modifier, errorMessage: String) {
    val finishDialog = remember { mutableStateOf(false) }
    if (!finishDialog.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {},
            title = { Text(text = "Error") },
            text = { Text(text = errorMessage) },
            confirmButton = {
                Button(
                    onClick = { finishDialog.value = true }
                ) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }

}