package com.doggie.app.view.search

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doggie.app.DoggieApp
import com.doggie.app.model.Dog
import com.doggie.app.util.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchViewModel : ViewModel() {
    var doggies: MutableLiveData<ArrayList<Dog>> = MutableLiveData(ArrayList())
    var errorMessage: MutableLiveData<String> = MutableLiveData()
    val temDog: ArrayList<Dog> = arrayListOf()
    var selectedDog: Dog? = null

    init {
        getDoggie()
    }

    fun removeItem(item: Dog) {
        val tempDoggie: ArrayList<Dog> = temDog
        tempDoggie.remove(item)
        doggies.postValue(tempDoggie)
    }

    fun updateSelectionStatus(dog: Dog?) {
        selectedDog = dog
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDoggie() {
        if(doggies.value?.isNotEmpty() == true) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = DoggieApp.mainRepository.getDoggie()) {
                is ResultOf.Success -> {
                    response.data.message.forEach {
                        val dNow = Date()
                        val ft = SimpleDateFormat("yyyyMMddHHmmssSS")
                        val datetime: String = ft.format(dNow)
                        temDog.add(Dog(id = datetime , dogProfile = it, selected = false))
                    }
                    doggies.postValue(temDog)
                }
                is ResultOf.Error -> {
                    println("error ${response.exception.message}")
                    errorMessage.postValue(response.exception.message)
                }
            }
        }
    }
}
