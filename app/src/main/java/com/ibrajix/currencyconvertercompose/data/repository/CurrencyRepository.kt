package com.ibrajix.currencyconvertercompose.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.ibrajix.currencyconvertercompose.data.response.ConversionResponse
import com.ibrajix.currencyconvertercompose.data.response.ExchangeRateHistory
import com.ibrajix.currencyconvertercompose.networking.CurrencyApiService
import com.ibrajix.currencyconvertercompose.view_model.Resource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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

    // Mock function for chart data (you'd implement a real API call in production)
    @RequiresApi(Build.VERSION_CODES.O)
    fun getExchangeRateHistory(from: String, to: String, days: Int): List<ExchangeRateHistory> {
        // This would be replaced with actual API data
        return List(days) { index ->
            val date = LocalDate.now().minusDays(days - index - 1L)
            // Simulate some variation
            val baseRate = 4.24
            val variation = (Math.random() - 0.5) * 0.1
            ExchangeRateHistory(
                date = date.format(DateTimeFormatter.ofPattern("dd MMM")),
                rate = baseRate + variation
            )
        }
    }
}