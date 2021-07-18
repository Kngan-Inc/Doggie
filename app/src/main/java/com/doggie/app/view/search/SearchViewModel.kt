package com.doggie.app.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doggie.app.DoggieApp
import com.doggie.app.util.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    var doggies: MutableLiveData<ArrayList<String>> = MutableLiveData(ArrayList())
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        getDoggie()
    }

    private fun getDoggie() {
        if(doggies.value?.isNotEmpty() == true) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = DoggieApp.mainRepository.getDoggie()) {
                is ResultOf.Success -> {
                    doggies.postValue(response.data.message)
                }
                is ResultOf.Error -> {
                    println("error ${response.exception.message}")
                    errorMessage.postValue(response.exception.message)
                }
            }
        }
    }
}
