package com.example.thefoodiezone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.thefoodiezone.databinding.ActivityMainBinding
import com.example.thefoodiezone.presentation.adapter.RestaurantAdapter
import com.example.thefoodiezone.presentation.viewModel.RestaurantsViewModel
import com.example.thefoodiezone.presentation.viewModel.RestaurantsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: RestaurantsViewModelFactory
    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter
    lateinit var viewModel: RestaurantsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[RestaurantsViewModel::class.java]
    }
}

