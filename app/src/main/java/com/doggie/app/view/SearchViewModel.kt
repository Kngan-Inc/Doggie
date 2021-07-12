package com.doggie.app.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.doggie.app.DoggieApp
import com.doggie.app.data.datasource.PassengerDataSource

class SearchViewModel : ViewModel() {
    val passengers = Pager(PagingConfig(pageSize = 10)) {
        PassengerDataSource(DoggieApp.mainRepository)
    }.flow.cachedIn(viewModelScope)

}
