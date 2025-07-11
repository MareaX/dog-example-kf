package com.example.dogexamplekf.dogs.presentation.doglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogexamplekf.databinding.FragmentDogListBinding
import com.example.dogexamplekf.dogs.presentation.UIState
import com.example.dogexamplekf.utils.Constants.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogListFragment : Fragment() {

    private val viewModel by viewModels<DogListViewModel>()
    private lateinit var binding: FragmentDogListBinding
    private val adapter by lazy {
        DogListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        viewModel.getDogList()
    }

    private fun setView() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            uiStateObserver(it)
        }
        binding.rvDogList.layoutManager = LinearLayoutManager(context)
        binding.rvDogList.adapter = adapter
    }

    private var uiStateObserver = { uiState: UIState ->
        when (uiState) {
            is UIState.Loading -> {
                binding.cpiLoader.visibility = View.VISIBLE
            }

            is UIState.DogListSuccess -> {
                binding.cpiLoader.visibility = View.GONE
                adapter.setData(uiState.dogList)
            }

            is UIState.Error -> {
                binding.cpiLoader.visibility = View.GONE
                context?.let {
                    showMessage(binding.root,it, uiState.errorMessage)
                }
            }
        }
    }
}