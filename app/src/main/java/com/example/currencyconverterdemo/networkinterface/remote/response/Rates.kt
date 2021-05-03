package com.example.currencyconverterdemo.networkinterface.remote.response

import com.google.gson.annotations.SerializedName

data class Rates(
    @SerializedName("currencyCode")
    val currencyCode: String,
    @SerializedName("currencyName")
    val currencyName: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("buyTT")
    val buyTT: String,
    @SerializedName("sellTT")
    val sellTT: String,
    @SerializedName("buyTC")
    val buyTC: String,
    @SerializedName("buyNotes")
    val buyNotes: String,
    @SerializedName("sellNotes")
    val sellNotes: String,
    @SerializedName("SpotRate_Date_Fmt")
    val SpotRate_Date_Fmt: String,
    @SerializedName("effectiveDate_Fmt")
    val effectiveDate_Fmt: String,
    @SerializedName("updateDate_Fmt")
    val updateDate_Fmt: String,
    @SerializedName("LASTUPDATED")
    val LASTUPDATED: String
) {
}