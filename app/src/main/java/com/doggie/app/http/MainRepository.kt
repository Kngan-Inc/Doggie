package com.doggie.app.http

import android.content.Context
import androidx.paging.DataSource
import com.doggie.app.model.BaseClass
import com.doggie.app.model.Dog
import com.doggie.app.util.ResultOf
import retrofit2.Response

class MainRepository(
    private val context: Context? = null,
    private val appService: AppService? = null
) {
    suspend fun getHund(): DataSource.Factory<Int, Dog> {
        return appService!!.getHound()
    }


    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultOf<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ResultOf.Success(response.body()!!)
            } else {
                ResultOf.Error(response.errorBody()?.toString() ?: "Something went wrong")
            }

        } catch (e: Exception) {
            ResultOf.Error(e.message ?: "Internet error runs")
        }
    }
}