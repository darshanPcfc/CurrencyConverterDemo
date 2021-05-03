package com.example.currencyconverterdemo.networkinterface.retrofit

import retrofit2.Response

/**
 * Created by Darshan Patel
 * Usage: general response for every API
 */
interface RetrofitResponseCallback {
    fun success(response: Response<*>)
    fun error(error: String)
    fun failure(message: String)
}