package com.example.currencyconverterdemo.di

import com.example.currencyconverterdemo.networkinterface.repository.MainApiRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Darshan Patel
 * Usage: include all your Repositories for KOIN
 * How to call: initialise through Koin
 * Useful parameter: context and api interface instance
 */

val repositoriesInjection = module {
    single { MainApiRepository(androidContext(), mainApiInterceptor = get()) }
}