package com.example.currencyconverterdemo.networkinterface.retrofit

import com.example.currencyconverterdemo.util.projectconstants.Constants
import retrofit2.Call
import retrofit2.http.GET

interface MainApiInterface {

    @GET(Constants.ApiEndpoints.GET_JSON_RATES)
    fun getCurrencyData(): Call<Any>
}