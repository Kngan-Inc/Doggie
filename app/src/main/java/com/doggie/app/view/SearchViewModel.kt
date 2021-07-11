package com.doggie.app.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import com.doggie.app.DoggieApp
import com.doggie.app.http.PagingDatabase
import com.doggie.app.http.User
import kotlinx.coroutines.*

class SearchViewModel : ViewModel() {

    val db by lazy {
        Room.inMemoryDatabaseBuilder(DoggieApp.appContext, PagingDatabase::class.java).build()
    }
    val pagedList: LiveData<PagedList<User>> by lazy {
        LivePagedListBuilder(
            db.userDao().dataSource,
            100
        ).build()
    }


    init {
        GlobalScope.launch {
            (1..3000).map {
                User(it)
            }.let { it ->
                it.groupBy {
                    it.uid / 200
                }.forEach { group ->
                    withContext(Dispatchers.Default) {
                        delay(group.key.toLong())
                        db.userDao().insertAll(group.value)
                    }
                }
            }
        }

    }
}
