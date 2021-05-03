package com.example.currencyconverterdemo.di

import com.example.currencyconverterdemo.ui.activity.MainActivityViewModel
import com.example.currencyconverterdemo.ui.fragment.CurrencyConverterViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Darshan Patel
 * Usage: include all your viewModels for KOIN
 * How to call: initialise through Koin
 * Useful parameter: appcontext and api interface instance
 */
val viewModelsInjection = module {
    viewModel { MainActivityViewModel(androidApplication(), get()) }
    viewModel { CurrencyConverterViewModel(androidApplication(), get()) }
}