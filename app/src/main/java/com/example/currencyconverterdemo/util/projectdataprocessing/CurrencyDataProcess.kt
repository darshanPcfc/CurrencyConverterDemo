package com.example.currencyconverterdemo.util.projectdataprocessing

import com.example.currencyconverterdemo.networkinterface.remote.response.Rates
import com.example.currencyconverterdemo.util.projectconstants.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Darshan Patel
 * Usage: Helper class to convert complex json of currency detail and transfer to rateList
 * Useful parameter: after fetching GET_JSON_RATES API response pass response().body in modifyPrices
 */
class CurrencyDataProcess {

    fun modifyPrices(str: String): List<Rates> {
        val rateDataList: MutableList<Rates> = ArrayList<Rates>()
        try {
            val inputJson = JSONObject(str)
            val gson = GsonBuilder().disableHtmlEscaping().create()
            val rawRateData: MutableList<Any> = ArrayList<Any>()
            var currencyRateMap: HashMap<String, Rates> = HashMap<String, Rates>()
            val productsRawData =
                inputJson.getJSONObject(Constants.JSON_OBJ_VALUE_DATA)
                    .getJSONObject(Constants.JSON_OBJ_VALUE_BRANDS)
                    .getJSONObject(Constants.JSON_OBJ_VALUE_WBC)
                    .getJSONObject(Constants.JSON_OBJ_VALUE_PORTFOLIOS)
                    .getJSONObject(Constants.JSON_OBJ_VALUE_FX)
                    .getJSONObject(Constants.JSON_OBJ_VALUE_PRODUCTS)
            val productsMap: Map<String, Any> = Gson().fromJson(
                productsRawData.toString(), object : TypeToken<HashMap<String?, Any?>?>() {}.type
            )
            productsMap.forEach { (key, value) ->
                val yyyy = gson.toJson(value)
                val finalData = JSONObject(yyyy)
                val listonnn = finalData.getJSONObject(Constants.JSON_OBJ_VALUE_RATES)
                rawRateData.add(listonnn)
            }
            rawRateData.forEach {
                currencyRateMap = Gson().fromJson(
                    it.toString(), object : TypeToken<HashMap<String?, Rates?>?>() {}.type
                )
                rateDataList.addAll(currencyRateMap.values)
            }
            return rateDataList
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return rateDataList
    }
}