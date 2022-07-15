package com.doggie.app.data.repository

import android.content.Context
import com.doggie.app.data.http.AppService
import com.doggie.app.model.BaseClass
import com.doggie.app.util.ResultOf
import retrofit2.Response

class MainRepository(
    private val context: Context? = null,
    private val appService: AppService
) {

    suspend fun getDoggie(): ResultOf<BaseClass<ArrayList<String>>> {
        return safeApiCall {
            appService.getDoggie()
        }
    }


    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultOf<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                ResultOf.Success(response.body()!!)
            } else {
                throw IllegalArgumentException("Something went wrong")
            }

        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}