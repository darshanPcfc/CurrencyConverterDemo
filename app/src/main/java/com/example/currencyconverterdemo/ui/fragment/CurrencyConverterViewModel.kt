package com.example.currencyconverterdemo.ui.fragment

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.example.currencyconverterdemo.base.BaseViewModel
import com.example.currencyconverterdemo.networkinterface.remote.response.Rates
import com.example.currencyconverterdemo.networkinterface.repository.MainApiRepository
import com.example.currencyconverterdemo.networkinterface.retrofit.MainApiInterface

/**
 * Created by Darshan Patel
 * Usage: ViewModel for CurrencyConverterFragment
 * Useful parameter: appcontext and api interface instance extend your base viewmodel and pass your navigator
 * invoke: through koin mentioned in viewmodel.kt file
 */
class CurrencyConverterViewModel(application: Application, mainApiInterface: MainApiInterface) :
    BaseViewModel<ICurrencyConverterNavigator>() {
    val availableAmount = ObservableField<String>()
    private val mainApiRepository: MainApiRepository

    init {
        mainApiRepository = MainApiRepository(application, mainApiInterface)
        this.availableAmount.set("")
    }

    fun getCurrencyData(): LiveData<List<Rates>> {
        return mainApiRepository.getCurrencyData()
    }

    fun convertRates(){
        getNavigator()?.onConvertClicked(availableAmount.get().toString())
    }

    fun resetScreenData(){
        getNavigator()?.onResetClicked()
    }

    fun refreshScreen(){
        getNavigator()?.onRefreshClicked()
    }
}