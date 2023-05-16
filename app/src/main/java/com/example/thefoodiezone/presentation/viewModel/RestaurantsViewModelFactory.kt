package com.example.thefoodiezone.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thefoodiezone.domain.useCase.GetLocalRestaurantsUseCase
import com.example.thefoodiezone.domain.useCase.SearchLocalRestaurantsUseCase

class RestaurantsViewModelFactory
    (private val app: Application,
     private val getLocalRestaurantsUseCase: GetLocalRestaurantsUseCase,
     private val searchLocalRestaurantsUseCase: SearchLocalRestaurantsUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RestaurantsViewModel(app, getLocalRestaurantsUseCase, searchLocalRestaurantsUseCase) as T
    }
}