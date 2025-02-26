package com.ibrajix.currencyconvertercompose.data.response

data class ConversionResponse(
    val success: Boolean,
    val query: Query,
    val info: Info,
    val result: Double
)

data class Query(
    val from: String,
    val to: String,
    val amount: Double
)

data class Info(
    val timestamp: Long,
    val rate: Double
)

// For the chart data
data class ExchangeRateHistory(
    val date: String,
    val rate: Double
)