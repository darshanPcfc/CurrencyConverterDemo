package com.example.currencyconverterdemo.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.currencyconverterdemo.BR
import com.example.currencyconverterdemo.R
import com.example.currencyconverterdemo.base.BaseApplication
import com.example.currencyconverterdemo.base.BaseFragment
import com.example.currencyconverterdemo.databinding.FragmentCurrencyConverterBinding
import com.example.currencyconverterdemo.networkinterface.remote.response.Rates
import com.example.currencyconverterdemo.ui.adapter.CurrencySpinnerAdapter
import kotlinx.android.synthetic.main.fragment_currency_converter.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Darshan Patel
 * Usage: converts your input currency to AUD
 * Invokation: through NAV graph
 */
class CurrencyConverterFragment :
    BaseFragment<FragmentCurrencyConverterBinding, CurrencyConverterViewModel>(),
    ICurrencyConverterNavigator {

    override val viewModel: CurrencyConverterViewModel by viewModel()
    private lateinit var fargmentCurrencyConverterBinding: FragmentCurrencyConverterBinding
    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_currency_converter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fargmentCurrencyConverterBinding = this.viewDataBinding!!
        callApi()
    }

    private fun callApi() {
        hideKeyboard()
        fargmentCurrencyConverterBinding.progressbar.visibility = View.VISIBLE
        try {
            if (BaseApplication.hasNetwork()!!) {
                //viewModel.getCurrencyData().value?.let { modifyPrices(it) }
                viewModel.getCurrencyData().observe(viewLifecycleOwner, {
                    fargmentCurrencyConverterBinding.progressbar.visibility = View.GONE
                    if (it != null) {
                        setUpCurrencyDataSpinner(removeCurrencyNotTraded(it as MutableList<Rates>))
                    } else {
                        //display data error
                        displayFailureAlert(
                            getString(R.string.str_error),
                            getString(R.string.str_no_data)
                        )
                    }
                })
            } else {
                //display no internet alert
                displayFailureAlert(
                    getString(R.string.str_connection_error),
                    getString(R.string.str_no_internet)
                )
                fargmentCurrencyConverterBinding.progressbar.visibility = View.GONE
            }
        } catch (e: Throwable) {
            fargmentCurrencyConverterBinding.progressbar.visibility = View.GONE
            e.printStackTrace()
        }
    }

    private fun removeCurrencyNotTraded(list: MutableList<Rates>): List<Rates> {
        list.removeAll {
            it.buyTT == "N/A" || it.buyTT.contains("On App", true)
        }
        return list
    }

    private fun setUpCurrencyDataSpinner(list: List<Rates>) {
        val currencySpinnerAdapter = context?.let {
            CurrencySpinnerAdapter(
                it,
                R.layout.spinner_currency_row,
                list
            )
        }
        spn_currency.adapter = currencySpinnerAdapter
    }

    fun calculateConvertedAUD(currencyRate: Double, availableAmount: Double): String {
        val convertedAUD = availableAmount / currencyRate
        return String.format("%.2f", convertedAUD)
    }

    override fun onConvertClicked(availableAmount: String) {
        if (availableAmount.isEmpty()) {
            Toast.makeText(context, getString(R.string.txt_enter_amount), Toast.LENGTH_LONG).show()
        } else {
            hideKeyboard()
            val rates = spn_currency.selectedItem as Rates
            fargmentCurrencyConverterBinding.txtUpdate.text =
                getString(R.string.update_text) + rates.LASTUPDATED
            fargmentCurrencyConverterBinding.txtOneUnitPrice.text =
                getString(R.string.txt_one_unit) + rates.currencyCode + rates.buyTT
            fargmentCurrencyConverterBinding.txtCurrency.text =
                getString(R.string.txt_aud) + calculateConvertedAUD(
                    rates.buyTT.toDouble(),
                    availableAmount.toDouble()
                )
        }
    }

    override fun onResetClicked() {
        hideKeyboard()
        fargmentCurrencyConverterBinding.txtCurrency.text =
            getString(R.string.txt_aud) + getString(R.string.default_available_amount)
        fargmentCurrencyConverterBinding.txtOneUnitPrice.text = ""
        viewModel.availableAmount.set("")
        spn_currency.setSelection(0)
    }

    override fun onRefreshClicked() {
        callApi()
    }
}