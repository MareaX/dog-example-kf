package com.example.dogexamplekf.utils

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

object Constants {
    const val BASE_URL = "https://jsonblob.com/api"
    const val DATABASE_NAME = "dog_database"
    const val ERROR_MESSAGE = "Intente mas tarde"

    fun showMessage(view: View, context: Context, message: String){
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }
}