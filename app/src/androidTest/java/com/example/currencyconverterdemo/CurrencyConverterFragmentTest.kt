package com.example.currencyconverterdemo

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.currencyconverterdemo.ui.fragment.CurrencyConverterFragment
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

/**
 * Created by Darshan Patel
 * Usage: Testing calculation of conversion
 * How to call: Run this class separately
 */
class CurrencyConverterFragmentTest {
    @Test
    fun calculateConversion() {
        val currencyRate = 0.8027
        val availableAmount = "100"
        val testCaseValue = availableAmount.toDouble() / currencyRate

        assertThat(
            String.format("%.2f", testCaseValue),
            equalTo(
                CurrencyConverterFragment().calculateConvertedAUD(
                    currencyRate,
                    availableAmount.toDouble()
                )
            )
        )
    }
}