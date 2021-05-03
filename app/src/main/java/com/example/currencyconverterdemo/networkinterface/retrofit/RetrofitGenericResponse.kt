package com.example.currencyconverterdemo.networkinterface.retrofit

import com.example.currencyconverterdemo.util.projectlogs.CustomLogger.d
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Created by Darshan Patel
 * Usage: Retrofit call
 * How to call: RetrofitGenericResponse(yourAPIInterFace,ResponsePOJOClass)
 */
object RetrofitGenericResponse {
    private val TAG = RetrofitGenericResponse::class.java.simpleName
    fun <T> callRetrofit(call: Call<T>, retrofitResponseCallback: RetrofitResponseCallback) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    retrofitResponseCallback.success(response)
                } else {
                    try {
                        retrofitResponseCallback.error(response.errorBody()!!.string())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (response.raw().networkResponse != null) {
                    d(TAG, "onResponse: response is from NETWORK...")
                } else if (response.raw().cacheResponse != null
                    && response.raw().networkResponse == null
                ) {
                    d(TAG, "onResponse: response is from cache...")
                } else {
                    retrofitResponseCallback.error("")
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                retrofitResponseCallback.failure(t.toString())
            }
        })
    }
}