package com.example.dogexamplekf.dogs.presentation.doglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogexamplekf.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }
}