package com.doggie.app.data.http

import com.doggie.app.model.BaseClass
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

    @GET("breed/hound/images/random/10")
    suspend fun getDoggie(): Response<BaseClass<ArrayList<String>>>

}