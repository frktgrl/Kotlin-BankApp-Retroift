package com.ftgrl.bankingapp.model

import com.google.gson.annotations.SerializedName

data class CurrencyRates(
    @SerializedName("CAD")
    val CAD: Double,

    @SerializedName("EUR")
    val EUR: Double,

    @SerializedName("USD")
    val USD: Double
)

data class CurrencyResponse(
    @SerializedName("data")
    val data: CurrencyRates
)
