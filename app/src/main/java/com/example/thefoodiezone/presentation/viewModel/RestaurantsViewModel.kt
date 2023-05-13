package com.example.thefoodiezone.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thefoodiezone.data.model.APIResponse
import com.example.thefoodiezone.data.util.Resource
import com.example.thefoodiezone.domain.useCase.GetLocalRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RestaurantsViewModel(private val app: Application,
                           private val getLocalRestaurantsUseCase: GetLocalRestaurantsUseCase): AndroidViewModel(app) {

    val localRestaurants: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getLocalRestaurants(location: String, term: String) = viewModelScope.launch(Dispatchers.IO){

        localRestaurants.postValue(Resource.Loading())

        try {
            //to pass context as parameter here we need to extend Android view model instead view model
            if(isInternetAvailable(app)){
                val apiResult = getLocalRestaurantsUseCase.execute(location, term)
                localRestaurants.postValue(apiResult)
            }else{
                localRestaurants.postValue(Resource.Error("Internet is not available"))
            }
        }catch (e: Exception){
            localRestaurants.postValue(Resource.Error(e.message.toString()))
        }

    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}