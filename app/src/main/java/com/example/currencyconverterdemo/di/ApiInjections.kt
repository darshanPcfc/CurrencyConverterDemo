package com.example.currencyconverterdemo.di

import com.example.currencyconverterdemo.networkinterface.retrofit.MainApiInterface
import com.example.currencyconverterdemo.networkinterface.retrofit.createNetworkClient
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Darshan Patel
 * Usage: include all your api interfaces creation of retrofit network adapter
 * How to call: initialised through Koin
 */
private val retrofit: Retrofit = createNetworkClient()
//main api interface is using retrofit network adapter to make API calls
private val MAIN_API_INTERCEPTOR: MainApiInterface = retrofit.create(MainApiInterface::class.java)
//injectors for all your interceptors for network call goes here
val apiInjections = module {
    single { MAIN_API_INTERCEPTOR }
}