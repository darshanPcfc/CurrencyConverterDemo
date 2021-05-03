package com.example.currencyconverterdemo.ui.activity

import android.app.Application
import com.example.currencyconverterdemo.base.BaseViewModel
import com.example.currencyconverterdemo.networkinterface.repository.MainApiRepository
import com.example.currencyconverterdemo.networkinterface.retrofit.MainApiInterface

/**
 * Created by Darshan Patel
 * Usage: ViewModel for MainActivity
 * Useful parameter: appcontext and api interface instance extend your base viewmodel and pass your navigator
 */
class MainActivityViewModel(application: Application, mainApiInterface: MainApiInterface) :
    BaseViewModel<IMainActivityNavigator>() {

    private val mainApiRepository: MainApiRepository =
        MainApiRepository(application, mainApiInterface)
}