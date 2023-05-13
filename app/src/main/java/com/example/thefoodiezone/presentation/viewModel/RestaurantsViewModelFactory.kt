package com.example.thefoodiezone.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thefoodiezone.domain.useCase.GetLocalRestaurantsUseCase

class RestaurantsViewModelFactory(private val app: Application,
                                  private val getLocalRestaurantsUseCase: GetLocalRestaurantsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RestaurantsViewModel(app, getLocalRestaurantsUseCase) as T
    }
}