package com.androiddev.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.androiddev.weatherapp.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var viewModel: StartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        setupLiveData()

        binding.findCity.setOnClickListener {
            viewModel.getWeatherFromApi(
                binding.cityToFind.text.toString()
            )
        }
        binding.getFromDb.setOnClickListener {
            viewModel.getWeatherFromDb()
        }
    }

    private fun setupLiveData() {
        viewModel.weatherData.observe(viewLifecycleOwner) {
            binding.response.text = it.toString()
        }
    }
}