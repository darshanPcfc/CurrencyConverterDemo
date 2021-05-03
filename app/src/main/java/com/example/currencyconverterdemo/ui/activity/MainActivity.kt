package com.example.currencyconverterdemo.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.currencyconverterdemo.BR
import com.example.currencyconverterdemo.R
import com.example.currencyconverterdemo.base.BaseActivity
import com.example.currencyconverterdemo.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

/**
 * Created by Darshan Patel
 * Usage: main activity and launcher activity for app
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(),
    IMainActivityNavigator {

    override val viewModel: MainActivityViewModel by viewModel()
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}