package com.ibrajix.currencyconvertercompose.utils

/**
 * Utility class that maps ISO country codes to their respective currency codes
 */
object CountryCurrencyMapper {

    // Map of ISO country codes to their respective currency codes
    private val countryToCurrencyMap = mapOf(
        // North America
        "us" to "USD", // United States
        "ca" to "CAD", // Canada
        "mx" to "MXN", // Mexico

        // Europe
        "at" to "EUR", // Austria
        "be" to "EUR", // Belgium
        "cy" to "EUR", // Cyprus
        "ee" to "EUR", // Estonia
        "fi" to "EUR", // Finland
        "fr" to "EUR", // France
        "de" to "EUR", // Germany
        "gr" to "EUR", // Greece
        "ie" to "EUR", // Ireland
        "it" to "EUR", // Italy
        "lv" to "EUR", // Latvia
        "lt" to "EUR", // Lithuania
        "lu" to "EUR", // Luxembourg
        "mt" to "EUR", // Malta
        "nl" to "EUR", // Netherlands
        "pt" to "EUR", // Portugal
        "sk" to "EUR", // Slovakia
        "si" to "EUR", // Slovenia
        "es" to "EUR", // Spain
        "gb" to "GBP", // United Kingdom
        "ch" to "CHF", // Switzerland
        "se" to "SEK", // Sweden
        "no" to "NOK", // Norway
        "dk" to "DKK", // Denmark
        "pl" to "PLN", // Poland
        "cz" to "CZK", // Czech Republic
        "hu" to "HUF", // Hungary
        "ro" to "RON", // Romania
        "bg" to "BGN", // Bulgaria
        "hr" to "HRK", // Croatia

        // Asia
        "jp" to "JPY", // Japan
        "cn" to "CNY", // China
        "hk" to "HKD", // Hong Kong
        "in" to "INR", // India
        "id" to "IDR", // Indonesia
        "kr" to "KRW", // South Korea
        "my" to "MYR", // Malaysia
        "ph" to "PHP", // Philippines
        "sg" to "SGD", // Singapore
        "th" to "THB", // Thailand
        "vn" to "VND", // Vietnam
        "ae" to "AED", // United Arab Emirates

        // Oceania
        "au" to "AUD", // Australia
        "nz" to "NZD", // New Zealand

        // South America
        "ar" to "ARS", // Argentina
        "br" to "BRL", // Brazil
        "cl" to "CLP", // Chile
        "co" to "COP", // Colombia
        "pe" to "PEN", // Peru

        // Africa
        "eg" to "EGP", // Egypt
        "ng" to "NGN", // Nigeria
        "za" to "ZAR", // South Africa

        // Add more mappings as needed
    )

    /**
     * Get currency code from country code
     * @param countryCode ISO country code (2-letter), case insensitive
     * @return Currency code or USD as fallback
     */
    fun getCurrencyCode(countryCode: String): String {
        return countryToCurrencyMap[countryCode.lowercase()] ?: "USD"
    }

    /**
     * Get country code from currency code
     * @param currencyCode ISO currency code (3-letter), case insensitive
     * @return Country code or "us" as fallback
     */
    fun getCountryCode(currencyCode: String): String {
        return countryToCurrencyMap.entries
            .firstOrNull { it.value.equals(currencyCode, ignoreCase = true) }
            ?.key ?: "us"
    }
}