package com.example.currencyconverterdemo.ui.fragment

/**
 * Created by Darshan Patel
 * Usage: all clicks and navigation for CurrencyConverterFragment UI included here
 */
interface ICurrencyConverterNavigator {
    fun onConvertClicked(availableAmount : String)
    fun onResetClicked()
    fun onRefreshClicked()
}