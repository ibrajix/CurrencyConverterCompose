package com.ibrajix.currencyconvertercompose.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.currencyconvertercompose.data.repository.CurrencyRepository
import com.ibrajix.currencyconvertercompose.data.response.ExchangeRateHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _conversionState = MutableStateFlow<ConversionState>(ConversionState.Initial)
    val conversionState: StateFlow<ConversionState> = _conversionState

    private val _fromCurrency = MutableStateFlow("EUR")
    val fromCurrency: StateFlow<String> = _fromCurrency

    private val _toCurrency = MutableStateFlow("PLN")
    val toCurrency: StateFlow<String> = _toCurrency

    private val _amount = MutableStateFlow("1")
    val amount: StateFlow<String> = _amount

    private val _result = MutableStateFlow<Double?>(null)
    val result: StateFlow<Double?> = _result

    private val _exchangeRate = MutableStateFlow<Double?>(null)
    val exchangeRate: StateFlow<Double?> = _exchangeRate

    private val _historyTimeframe = MutableStateFlow(30)
    val historyTimeframe: StateFlow<Int> = _historyTimeframe

    private val _rateHistory = MutableStateFlow<List<ExchangeRateHistory>>(emptyList())
    val rateHistory: StateFlow<List<ExchangeRateHistory>> = _rateHistory

    // A map of country codes to currency codes to handle country selection
    private val countryToCurrencyMap = mapOf(
        "us" to "USD",
        "eu" to "EUR",
        "gb" to "GBP",
        "jp" to "JPY",
        "ca" to "CAD",
        "au" to "AUD",
        "ch" to "CHF",
        "cn" to "CNY",
        "hk" to "HKD",
        "nz" to "NZD",
        "pl" to "PLN"

    )

    fun updateFromCurrency(currency: String) {
        _fromCurrency.value = currency

        _result.value = null
    }

    fun updateToCurrency(currency: String) {
        _toCurrency.value = currency

        _result.value = null
    }

    fun updateAmount(amount: String) {
        _amount.value = amount

        _result.value = null
    }

    fun convertCurrency() {
        val amountValue = _amount.value.toDoubleOrNull() ?: return
        _conversionState.value = ConversionState.Loading

        viewModelScope.launch {
            try {
                val result = repository.convertCurrency(
                    fromCurrency.value,
                    toCurrency.value,
                    amountValue
                )

                when (result) {
                    is Resource.Success -> {
                        _result.value = result.data.result
                        _exchangeRate.value = result.data.info.rate
                        _conversionState.value = ConversionState.Success
                    }

                    is Resource.Error -> {
                        _conversionState.value = ConversionState.Error(result.message)
                    }
                }
            } catch (e: Exception) {
                _conversionState.value = ConversionState.Error(e.message ?: "Unknown error")
            }
        }
    }

    
}

sealed class ConversionState {
    data object Initial : ConversionState()
    data object Loading : ConversionState()
    data object Success : ConversionState()
    data class Error(val message: String) : ConversionState()
}

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}