package com.doggie.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.doggie.app.data.repository.MainRepository
import com.doggie.app.data.http.appService

class DoggieApp : Application() {
    companion object {
        lateinit var appContext: Context
            private set

        @SuppressLint("StaticFieldLeak")
        lateinit var mainRepository: MainRepository
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        mainRepository = MainRepository(context = this, appService = appService)
    }
}