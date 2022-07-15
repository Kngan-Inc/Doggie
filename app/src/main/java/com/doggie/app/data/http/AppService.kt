package com.doggie.app.data.http

import com.doggie.app.model.BaseClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET("breed/hound/images/random/10")
    suspend fun getDoggie(): Response<BaseClass<ArrayList<String>>>

}