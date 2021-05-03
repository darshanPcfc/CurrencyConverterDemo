package com.example.currencyconverterdemo.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.currencyconverterdemo.R
import com.example.currencyconverterdemo.networkinterface.remote.response.Rates
import java.util.*

class CurrencySpinnerAdapter(
    context: Context,
    textViewResourceId: Int,
    private val values: List<Rates>
) : ArrayAdapter<Rates>(context, textViewResourceId, values) {

    override fun getCount() = values.size
    override fun getItem(position: Int) = values[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.text = values[position].country
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.text = values[position].country
        return label
    }
}