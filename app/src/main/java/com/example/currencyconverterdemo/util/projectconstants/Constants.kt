package com.example.currencyconverterdemo.util.projectconstants

/**
 * Created by Darshan Patel
 * Usage: all project constant
 */
object Constants {
    const val JSON_OBJ_VALUE_DATA = "data"
    const val JSON_OBJ_VALUE_BRANDS = "Brands"
    const val JSON_OBJ_VALUE_WBC = "WBC"
    const val JSON_OBJ_VALUE_PORTFOLIOS = "Portfolios"
    const val JSON_OBJ_VALUE_FX = "FX"
    const val JSON_OBJ_VALUE_PRODUCTS = "Products"
    const val JSON_OBJ_VALUE_RATES = "Rates"

    // Retrofit file cache name
    var retrofitCacheFile = "dataServiceCacheFile"

    interface ApiEndpoints {
        companion object {
            const val GET_JSON_RATES = "getJsonRates.wbc.fx.json"
        }
    }
}