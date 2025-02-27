package com.ibrajix.currencyconvertercompose.data.model

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)

// List of common currencies for fallback
val commonCurrencies = listOf(
    Currency("USD", "US Dollar", "$"),
    Currency("EUR", "Euro", "€"),
    Currency("GBP", "British Pound", "£"),
    Currency("JPY", "Japanese Yen", "¥"),
    Currency("AUD", "Australian Dollar", "A$"),
    Currency("CAD", "Canadian Dollar", "C$"),
    Currency("CHF", "Swiss Franc", "CHF"),
    Currency("CNY", "Chinese Yuan", "¥"),
    Currency("INR", "Indian Rupee", "₹"),
    Currency("PLN", "Polish Złoty", "zł"),
    // Add more currencies as needed
)

// Fallback function in case the country picker doesn't have the currency
fun getCurrencyByCode(code: String): Currency {
    return commonCurrencies.find { it.code == code }
        ?: Currency(code, code, code)
}