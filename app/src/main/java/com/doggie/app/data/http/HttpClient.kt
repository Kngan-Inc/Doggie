package com.doggie.app.data.http

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.doggie.app.DoggieApp
import com.doggie.app.util.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private val httpClient = OkHttpClient
    .Builder()
    .addInterceptor(ChuckerInterceptor.Builder(DoggieApp.appContext).build())
    .build()

private val retrofit = Retrofit
    .Builder()
    .client(httpClient)
    .baseUrl(Constant.url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal val appService: AppService = retrofit.create()