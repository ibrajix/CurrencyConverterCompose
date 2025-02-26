package com.ibrajix.currencyconvertercompose.data.repository

import com.ibrajix.currencyconvertercompose.data.response.ConversionResponse
import com.ibrajix.currencyconvertercompose.networking.CurrencyApiService
import com.ibrajix.currencyconvertercompose.view_model.Resource
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val apiService: CurrencyApiService
) {
    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double
    ): Resource<ConversionResponse> {
        return try {
            val response = apiService.convertCurrency(to, from, amount)
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}