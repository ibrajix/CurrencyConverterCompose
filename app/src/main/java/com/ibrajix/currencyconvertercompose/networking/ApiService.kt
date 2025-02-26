package com.ibrajix.currencyconvertercompose.networking

import com.ibrajix.currencyconvertercompose.data.response.ConversionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("convert")
    suspend fun convertCurrency(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: Double
    ): ConversionResponse
}