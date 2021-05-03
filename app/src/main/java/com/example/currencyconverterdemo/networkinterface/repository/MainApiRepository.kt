package com.example.currencyconverterdemo.networkinterface.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currencyconverterdemo.BuildConfig
import com.example.currencyconverterdemo.networkinterface.remote.response.Rates
import com.example.currencyconverterdemo.networkinterface.retrofit.MainApiInterface
import com.example.currencyconverterdemo.networkinterface.retrofit.RetrofitGenericResponse
import com.example.currencyconverterdemo.networkinterface.retrofit.RetrofitResponseCallback
import com.example.currencyconverterdemo.util.projectconstants.Constants
import com.example.currencyconverterdemo.util.projectdataprocessing.CurrencyDataProcess
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response

/**
 * Created by Darshan Patel
 * Usage: Repository to call API
 * How to call: create a instance and pass instance of your API interface and pass your context
 * Useful parameter: mainApiInterceptor as required to call retrofit
 */
class MainApiRepository(
    private val context: Context,
    val mainApiInterceptor: MainApiInterface
) {
    fun getCurrencyData(): LiveData<List<Rates>> {
        val data = MutableLiveData<List<Rates>>()

        RetrofitGenericResponse.callRetrofit(mainApiInterceptor.getCurrencyData(), object :
            RetrofitResponseCallback {
            override fun success(response: Response<*>) {
                if (response.body() != null) {
                    val gson = Gson()
                    data.value = CurrencyDataProcess().modifyPrices(gson.toJson(response.body()))
                }
            }

            override fun error(error: String) {
                data.value = null
            }

            override fun failure(message: String) {
                data.value = null
            }
        })
        return data
    }

    companion object {
        private val TAG = MainApiRepository::class.java.simpleName
    }
}