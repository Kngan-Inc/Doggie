package com.doggie.app.data.http

import androidx.paging.DataSource
import com.doggie.app.model.Dog
import com.doggie.app.model.PassengersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET("passenger")
    suspend fun getPassengersData(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): Response<PassengersResponse>

}