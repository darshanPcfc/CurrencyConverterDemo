package com.example.currencyconverterdemo.base

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.currencyconverterdemo.di.apiInjections
import com.example.currencyconverterdemo.di.repositoriesInjection
import com.example.currencyconverterdemo.di.viewModelsInjection
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Darshan Patel
 * Usage: application class to initiate Koin and app level functions and working
 */
//Declare all application component here
class BaseApplication : Application() {
    //checking internet connectivity for user
    private val isNetworkConnected: Boolean
        get() {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    //for check internet over Bluetooth
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } else {
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                return nwInfo.isConnected
            }
        }

    override fun onCreate() {
        super.onCreate()
        initialiseKoin()
        if (instance == null) {
            instance = this
        }
    }

    //init koin
    //module list will create all modules injection which can be further used throughout application
    private fun initialiseKoin() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(apiInjections, repositoriesInjection, viewModelsInjection))
        }
    }

    companion object {
        //initial values required while launching application
        var instance: BaseApplication? = null
            private set

        //checks internet connection
        fun hasNetwork(): Boolean? {
            return instance?.isNetworkConnected
        }
    }
}