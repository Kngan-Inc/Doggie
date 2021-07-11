package com.doggie.app.http

import androidx.paging.DataSource
import com.doggie.app.model.BaseClass
import com.doggie.app.model.Dog
import com.doggie.app.util.ResultOf
import retrofit2.Response
import retrofit2.http.GET

interface AppService {
    @GET("breed/hound/images")
    suspend fun getHound(): DataSource.Factory<Int, Dog>
}